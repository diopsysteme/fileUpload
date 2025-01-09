

L'appli est en Spring BOOT permettant de faire les actions suivantes :  stockage (en local ou dans la base de donnes selon ce que le user choisi ), liste, téléchargement et suppression. Elle offre une API RESTful pour interagir avec les fonctionnalités.
Elle est documentee avec swagger utilisez ce lien https://fileupload-elgn.onrender.com/swagger-ui/index.html
elle est dockerisée et Monitorée 

Prérequis

    Java 21 installé
    Maven 
    Base de données PostgreSQL (Neon Postgres pour acces a tous)
    
Installation

    Cloner le repository:
    Bash

    git clone https://github.com/diopsysteme/fileUpload

    Importer le projet dans votre editeur
    Configurer la base de données:
        Mettre à jour les propriétés de connexion à la base de données dans le fichier application.yml par defaut c'est une base de donne neon Postgres .
    Lancer l'application:
        Exécuter la classe principale de l'application Spring Boot.
    mvn clean install 
Utilisation de l'API
Endpoints
ensuite faudra creer un user avec {
login,
password} grace à l'ndpoint /user (method POST)
puis se connecter avec l'endpoint /login
ensuite mettez le token dans les hedears avec le Bearer 
  
    POST /files :
        Corps de la requête: Multipart/form-data contenant le fichier à uploader.
        Réponse: 201 Created avec l'ID du fichier créé.
    GET /files :
        Réponse: 200 OK avec une liste de fichiers (nom, taille, type MIME, date d'upload).
    GET /files/{id}/download :
        Réponse: 200 OK avec le fichier à télécharger.
    DELETE /files/{id} :
        Réponse: 204 No Content si la suppression a réussi.


Il ya des endpoint supplementaire mais ils ne font pas parti des livrables  
Documentation de l'API

La documentation détaillée de l'API est générée automatiquement avec Swagger/OpenAPI et est accessible à l'adresse http://localhost:8080/swagger-ui.html ça c en local ctd si vous avez cloner et demarrer le projet 
sinon si vous n'avez pas clonez utiliser cet url https://fileupload-elgn.onrender.com/swagger-ui/index.html.
Fonctionnalités supplémentaires

    Validation des fichiers: Taille maximale, types autorisés. avec un Custom Validator
    Limite de taille totale des fichiers: 10 Mo.
    Stockage des fichiers: En local comme en base de donnee.
    Authentification: Avec Spring Securite on a use le DaoAuthenticationProvider et avec token .
    Pagination et recherche:on donne dans la ligne de requette ?query=<le nom du fichier a rechercher" ?page pour le numero de page et ?size pour le offset combien d'element on doit prendre par defaut il prends 10 si on ne precise rien .



