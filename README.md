--- 

## Requirements 
- JDK 8 or higher


## How to run 

## Start the RMI Registry 
1- open cmd prompt 
2- cd into where the 'GuessTheNumberGame' folder is saved
3- type 'rmiregistry 2000'

## Start the server
1- open 'another' cmd prompt
2- cd into where the 'GuessTheNumberGame' folder is saved
3- type java -cp bin server.GuessTheNumberServer 

The server will be up and state 'Game server is ready!' 

## Start the client(s) 
1- open 'another' cmd prompt
2- cd into where the 'GuessTheNumberGame' folder is saved
3- type java -cp bin client.GuessTheNumberClient

The client will run the program and will prompt with the 'enter your name' after that enjoy the rest of your game.

