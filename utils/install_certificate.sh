#!/bin/bash
# Add the staging option (--staging) to certbot-auto if you wish to validate the procedure
DEST=/home/usmo/keystore
DOMAIN=olivet-tt.fr
EMAIL=qliger.dev@gmail.com

mkdir -p /opt/certbot
wget https://dl.eff.org/certbot-auto -O /opt/certbot/certbot-auto
chmod a+x /opt/certbot/certbot-auto
/opt/certbot/certbot-auto certonly --debug --non-interactive --email ${EMAIL} --agree-tos --standalone -d ${DOMAIN} --keep-until-expiring
openssl pkcs12 -export -in /etc/letsencrypt/live/${DOMAIN}/cert.pem -inkey /etc/letsencrypt/live/${DOMAIN}/privkey.pem -out ${DEST} -name tomcat -CAfile /etc/letsencrypt/live/${DOMAIN}/chain.pem -caname root -passout pass:password