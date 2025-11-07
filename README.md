# scrimlab

# Lancement de la DB

dans le dossier src/main/resources/db :

`` sudo docker build -t scrimlab-database . ``

`` sudo docker run -d -p 5432:5432 --name scrimlab-database scrimlab-database  ``

### lancement de la DB :

`` sudo docker start scrimlab-database ``

### éteindre de la DB :

`` sudo docker stop scrimlab-database ``

### détruire de la DB :

`` sudo docker destroy scrimlab-database ``