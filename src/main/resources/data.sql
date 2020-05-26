INSERT INTO `user` VALUES
(1, 'admin@gmail.com', '$2a$10$KMlIdTThJPcd5chekN60TuWOs2EQWUsAkHO/DDKUAi8Y.G0fkm7hm', 'ADMIN', 'admin');

INSERT INTO `article` VALUES
(1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt augue sed eleifend convallis. Mauris risus magna, faucibus ut augue non, volutpat faucibus dolor. Etiam ultrices porta massa id congue. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.',
 '2020-03-25 10:05:00', 'Compétition', '/uploads/img/article/refonte_du_site.webp', 'Refonte du site'),
(2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt augue sed eleifend convallis. Mauris risus magna, faucibus ut augue non, volutpat faucibus dolor. Etiam ultrices porta massa id congue. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.',
 '2020-03-26 13:46:00', 'Stage', '/uploads/img/article/test_article.webp', 'Test article');

INSERT INTO `sponsor` (id, name, picture_path, picture_path_bis, site_url) VALUES
(1, "Olivet", '/uploads/img/sponsor/olivet.png', '/uploads/img/sponsor/olivet_bis.png', 'http://olivet.fr/fr'),
(2, "Département du Loiret", '/uploads/img/sponsor/département_du_loiret.png', '/uploads/img/sponsor/département_du_loiret_bis.png', 'https://www.loiret.fr/'),
(3, "Comité du Loiret", '/uploads/img/sponsor/comité_du_loiret.png', '/uploads/img/sponsor/comité_du_loiret_bis.png', 'https://www.cd45tt.com/'),
(4, "Ping Passion", '/uploads/img/sponsor/ping_passion.png', '/uploads/img/sponsor/ping_passion_bis.png', 'https://www.ping-passion.com/'),
(5, "Sim", '/uploads/img/sponsor/sim.png', '/uploads/img/sponsor/sim_bis.png', 'https://www.sim-emploi.net/'),
(6, "Covifruit", '/uploads/img/sponsor/covifruit.png', '/uploads/img/sponsor/covifruit_bis.png', 'https://www.covifruit.com/'),
(7, "La Boucherie", '/uploads/img/sponsor/la_boucherie.png', '/uploads/img/sponsor/la_boucherie_bis.png', 'https://www.la-boucherie.fr/'),
(8, "ADA Réseaux", '/uploads/img/sponsor/ada_réseaux.png', '/uploads/img/sponsor/ada_réseaux_bis.png', 'https://www.ada-reseaux.fr/'),
(9, "Bozkurt", '/uploads/img/sponsor/bozkurt.png', '/uploads/img/sponsor/bozkurt_bis.png', ''),
(10, "ProtexSSI", '/uploads/img/sponsor/protexssi.png', '/uploads/img/sponsor/protexssi_bis.png', 'https://www.protexssi.fr/'),
(11, "Avisofi", '/uploads/img/sponsor/avisofi.png', '/uploads/img/sponsor/avisofi_bis.png', 'https://avisofi-credit-immobilier.fr/'),
(12, "VDL Conseil", '/uploads/img/sponsor/vdl_conseil.png', '/uploads/img/sponsor/vdl_conseil_bis.png', 'http://www.vdlconseil.fr/'),
(13, "SOCA 45", '/uploads/img/sponsor/soca_45.png', '/uploads/img/sponsor/soca_45_bis.png', 'http://www.soca-45.com/');

INSERT INTO `trainer` (id, firstname, lastname, picture_path, informations) VALUES
(1, "Guillaume", "le Guigner", "/uploads/img/entrainement/guillaume_le_guigner.jpg", "Infos"),
(2, "Antoine", "Biston", "/uploads/img/entrainement/antoine_biston.jpg", "Infos");

INSERT INTO `schedule` (id, title, day, data_start, data_end, content, position, color) VALUES
(1, "Perfectionnement -9 | -11", "Mardi", "16:15", "18:00", "Contenu", "center", "#dbc248"),
(2, "Perfectionnement -13 | -15", "Mardi", "18:00", "19:30", "Contenu", "center", "#bf4040"),
(3, "Dirigé National", "Mardi", "19:30", "21:30", "Contenu", "left", "#2dc856"),
(4, "Organisé Dép + Rég", "Mardi", "19:30", "21:30", "Contenu", "right", "#48c0db"),
(5, "Découverte -9 | -11", "Mercredi", "14:30", "16:00", "Contenu", "center", "#dbc248"),
(6, "Collèges -13 | -15", "Mercredi", "16:00", "18:30", "Contenu", "center", "#bf4040"),
(7, "Perfectionnement -15 | -18", "Mercredi", "19:00", "20:30", "Contenu", "left", "#dc2e79"),
(8, "Loisirs", "Mercredi", "20:00", "22:00", "Contenu", "right", "#48c0db"),
(9, "Perfectionnement -9 | -11", "Jeudi", "16:15", "18:00", "Contenu", "center", "#dbc248"),
(10, "Perfectionnement -13 | -15", "Jeudi", "18:00", "19:30", "Contenu", "center", "#bf4040"),
(11, "Libre National", "Jeudi", "19:30", "21:30", "Contenu", "left", "#2dc856"),
(12, "Dirigé Dép + Rég", "Jeudi", "19:30", "21:30", "Contenu", "right", "#48c0db"),
(13, "Perfectionnement -9 | -11", "Vendredi", "16:15", "18:00", "Contenu", "center", "#dbc248"),
(14, "Perfectionnement + Compétition -11 | -13 | -15 | -18", "Vendredi", "18:00", "19:30", "Contenu", "center", "#de7c3f"),
(15, "Compétitions par équipes + Salle ouverte libre", "Vendredi", "19:30", "22:00", "Contenu", "center", "#5c8699");


INSERT INTO `member` (id, firstname, lastname, role, picture_path) VALUES
(1, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg"),
(2, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg"),
(3, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg"),
(4, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg"),
(5, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg"),
(6, "Fabien", "Gasnier", "Président du club", "/uploads/img/staff/fabien_gasnier.jpg");

INSERT INTO `contact` (id, mail, phone, address, facebook_link, youtube_link) VALUES
(1, "usmo.tennisdetable@wanadoo.fr", "02 38 66 86 88", "199 Rue des Cireries, 45160 Olivet", "https://www.facebook.com/usmolivetennisdetable/", "#");