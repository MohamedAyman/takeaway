# takeaway
### Description:

Two microservice spring boot game run in docker container to facilitate the environment.
The communication between the two service is done by rest api or if the service is not available a message is sent using kafka.

To run the application make sure you are at the root directory then run :
```
docker-compose up --build
```

you can change the game from automatic to manual and who start the game from the DockerFile

this will make the player automatic and start first
```
AUTOMATIC_PLAYER: "true"
FIRST_MOVE: "true"
```
-----------------------------------------------------
this will make the player manual and start first
```
AUTOMATIC_PLAYER: "false"
FIRST_MOVE: "true"
```
-----------------------------------------------------

to start the game and send number to other player in manual game

you need to use this api
```
post localhost:5001/player

body { "current_number": 56 }
```
to see player logs
```
get localhost:5001/player/log
```
