package com.ttolivet.usmolivet.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ttolivet.usmolivet.entities.Player;
import com.ttolivet.usmolivet.entities.Poule;
import com.ttolivet.usmolivet.entities.Team;
import com.ttolivet.usmolivet.repositories.PouleRepository;
import com.ttolivet.usmolivet.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ConnectApi {

    private final RestTemplate restTemplate;
    private final String identifiant = "SW585";
    private final String serieNumber = "04PUVCQX55HA26P";
    private final String password = "D9tX2rY7cJ";
    private String baseParams = "";
    private String tm = "";
    private String tmc = "";

    @Autowired
    PouleRepository pouleRepository;

    @Autowired
    TeamRepository teamRepository;


    public ConnectApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void initUser() {
        updateCredentials();
        String url = "http://www.fftt.com/mobile/pxml/xml_initialisation.php" + this.baseParams;
        System.out.println(this.restTemplate.getForObject(url, String.class));
    }

    public List<Player> getClubPlayers() {
        updateCredentials();
        String url = "http://www.fftt.com/mobile/pxml/xml_liste_joueur.php" + this.baseParams + "&club=04450410";
        String response = this.restTemplate.getForObject(url, String.class);

        XmlMapper xmlMapper = new XmlMapper();
        List<Player> players = new ArrayList<>();
        try {
            players = xmlMapper.readValue(response, new TypeReference<List<Player>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return getPlayersInfos(players);
    }

    public List<Player> getPlayersInfos(List<Player> players) {
        XmlMapper xmlMapper = new XmlMapper();
        List<Player> finalPlayers = new ArrayList<>();
        for (Player player : players) {
            updateCredentials();
            String url = "http://www.fftt.com/mobile/pxml/xml_joueur.php" + this.baseParams + "&licence=" + player.getLicence();
            String response = this.restTemplate.getForObject(url, String.class);
            try {
                finalPlayers.add(xmlMapper.readValue(response, new TypeReference<List<Player>>() {}).get(0));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return finalPlayers;
    }

    public void getTeams() {
        teamRepository.deleteAll();
        pouleRepository.deleteAll();

        updateCredentials();
        String url = "http://www.fftt.com/mobile/pxml/xml_equipe.php" + this.baseParams + "&numclu=04450410&type=A";
        String response = this.restTemplate.getForObject(url, String.class);

        XmlMapper xmlMapper = new XmlMapper();
        List<Poule> poules = new ArrayList<>();
        try {
            poules = xmlMapper.readValue(response, new TypeReference<List<Poule>>() {});
            pouleRepository.saveAll(poules);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        getTeamsResults(poules);
    }

    public void getTeamsResults(List<Poule> poules) {
        XmlMapper xmlMapper = new XmlMapper();

        for (Poule poule : poules) {
            Pattern r = Pattern.compile("(?<==)(.*?)(?=&)");
            Matcher m = r.matcher(poule.getLienDivision());
            m.find();
            int pouleId = Integer.parseInt(m.group());
            m.find();
            String divisionId = m.group();

            updateCredentials();
            String url = "http://www.fftt.com/mobile/pxml/xml_result_equ.php" + this.baseParams + "&action=classement" + "&auto=1" + "&D1=" + divisionId + "&cx_poule=" + pouleId;
            String response = this.restTemplate.getForObject(url, String.class);
            try {
                List<Team> teams = xmlMapper.readValue(response, new TypeReference<List<Team>>() {});
                for (Team team : teams) {
                    team.setPoule(poule);
                }
                teamRepository.saveAll(teams);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public String encodeKey(String passwordToHash) {
        String hashPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }

    public String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((encodeKey(keyString)).getBytes("UTF-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);

            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
        } catch (InvalidKeyException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        return digest;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHMMSSmmm");
        return sdf.format(new Date());
    }

    public void updateCredentials() {
        this.tm = getDate();
        this.tmc = hmacDigest(tm, this.password, "HmacSHA1");
        this.baseParams = "?serie=" + this.serieNumber + "&tm=" + this.tm + "&tmc=" + this.tmc + "&id=" + this.identifiant;
    }


}
