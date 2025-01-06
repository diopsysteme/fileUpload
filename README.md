

Cette application Spring Boot permet de gérer des fichiers : upload, stockage, liste, téléchargement et suppression. Elle offre une API RESTful pour interagir avec les fonctionnalités.
Elle est documentee avec swagger utilisez ce lien https://fileupload-elgn.onrender.com/swagger-ui/index.html

Prérequis

    Java 21 installé
    Maven 
    Base de données PostgreSQL 

Installation

    Cloner le repository:
    Bash

    git clone https://github.com/diopsysteme/fileUpload

    Importer le projet dans votre editeur
    Configurer la base de données:
        Mettre à jour les propriétés de connexion à la base de données dans le fichier application.yml par defaut c'est une base de donne neon Postgres .
    Lancer l'application:
        Exécuter la classe principale de l'application Spring Boot.

Utilisation de l'API
Endpoints
ensuite faudra creer un user avec {
login,
password}
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

Exemple avec Postman

    Upload d'un fichier:
        Méthode: POST
        URL: http://localhost:8080/files
        Body: Multipart/form-data avec votre fichier
    Liste des fichiers:
        Méthode: GET
        URL: http://localhost:8080/files
Il ya des endpoint supplementaire mais ils ne font pas parti des livrables 
Documentation de l'API

La documentation détaillée de l'API est générée automatiquement avec Swagger/OpenAPI et est accessible à l'adresse http://localhost:8080/swagger-ui.html ça c en local ctd si vous avez cloner et demarrer le projet 
sinon si vous n'avez pas clonez utiliser cet urlhttps://fileupload-elgn.onrender.com/swagger-ui/index.html.
Fonctionnalités supplémentaires

    Validation des fichiers: Taille maximale, types autorisés.
    Limite de taille totale des fichiers: 10 Mo.
    Stockage des fichiers: [Préciser le mode de stockage choisi (système de fichiers, BLOB)].
    Authentification: [Si implémentée, préciser le mécanisme].
    Pagination et recherche: [Si implémentée, préciser les paramètres].



