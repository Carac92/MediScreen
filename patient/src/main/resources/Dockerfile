# Utilisez l'image de base MySQL
FROM mysql

# Copiez le script SQL dans le répertoire /docker-entrypoint-initdb.d du conteneur
COPY script.sql /docker-entrypoint-initdb.d/

# Définissez les variables d'environnement pour la configuration MySQL
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=patientdb
# Définissez la variable d'environnement pour le répertoire de données MySQL
ENV MYSQL_DATA_DIR /var/lib/mysql
COPY script.sql .

# Exposez le port MySQL (33060)
EXPOSE 3306

# Démarrez le serveur MySQL lors de l'exécution du conteneur
CMD ["mysqld"]
VOLUME ["$MYSQL_DATA_DIR"]