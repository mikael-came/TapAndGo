# TapAndGo

## Prerequis

Il est nécessaire pour le fonctionnemnt de l'application de fournir une base de donnée Mongo.
https://docs.mongodb.com/manual/installation/
La base de donénes est initialisé au chargement de l'application
    
pour Linux  
    
    `service mongod start`



## Quick start

    $ git clone https://github.com/mikael-came/TapAndGo
    $ cd TapAndGo
    $ service mongod start
    $ ./gradlew bootRun
    
    
  se connecter à l'application: http://localhost:8080/admin/  
  
  http://localhost:8080/api pour accéder à l'api
  
  http://localhost:8080/admin pour accéder à la console d'administration 
    

## Identifiant de connexion
Role Administateur : admin/admin

Role User : user/user
 
 ## Quick run du JAR (non testé)
 Le jar compilé suivant, permet de runner l'application (jar embarqué): /build/libs/bikeapp-0.0.1-SNAPSHOT.jar
 Pour le lancer jouer la commande suivant :
 
      java -jar build/libs/bikeapp-0.0.1-SNAPSHOT.jar
