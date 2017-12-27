# Battleships
[![Build Status](https://travis-ci.org/szczepanskikrs/Battleships.svg?branch=server-client-basics)](https://travis-ci.org/szczepanskikrs/Battleships)

Battleship game over the network using Java client - server architecture.
## Getting Started
### Prerequisites
In order to run this game you need to install:
* [Maven 3.5.0](https://maven.apache.org)
* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
### Installing
In order to install you need to run this command in main game directory:

```
mvn install
```

## Running
Step 1: Get to directory where you cloned the repository in order to do so use:
```
cd /home/ ... /Battleships
```
Step 2: When you are in correct folder and you have JDK and Maven installed use command to compile code to .jar file.
```
mvn install
```
Step 3: Change directory to the element you want to run for example client/target
```
cd client/target
```
Step 4: Run module that you want with command:
```
java -jar <modulename>.jar
```
Step 5: Runing server module require use of -Dport=<portNumber> command:
```
java -Dport=SamplePortNumber -jar <modulename>.jar 
```
## Gameplay
In order to play you need to run client.

<p align="center">
  <img src="https://preview.ibb.co/jgC2n6/2017_12_26_1.png">
</p>

Login window view. You should fill all needed fields. <br/>

<p align="center">
<img src="https://image.ibb.co/d3Oa76/2017_12_26.png">
</p> <br/>

When you provide incorrect data popup will appear and you won't be able to log in.

<p align="center">
<img src="https://preview.ibb.co/c4Ddum/2017_12_26_2.png">
</p> <br/>

Game is based on salvos, you have as many shots as undamaged masts of your ships.

<p align="center">
<img src="https://preview.ibb.co/f7Z1Em/2017_12_26_3.png">
</p> <br/>

Results of each salvo will be displayed when both players finished firing their shots. 
Salvo counter will reduce every time you lose a mast. 
<p align="center">
<img src="https://image.ibb.co/hefRzw/2017_12_27_075255_362x179_scrot.png">
  </p> <br/>
  Lose popup as above will be displayed if you lose.
  <p align="center">
<img src="https://image.ibb.co/mc6zKw/2017_12_27_075303_362x182_scrot.png"><br/>
    </p> <br/>
Victory popup as above will be displayed if you won.
 <p align="center">
<img src="https://image.ibb.co/ea6Yew/2017_12_27_075500_362x182_scrot.png"><br/>
   </p> <br/>
   Draw popup as above will be displayer if draw occured.
<br/>
Game ends when one of players lose all ships or both players lose all ships at once then draw happens. 

## Built With

* [IntelliJ Idea](https://www.jetbrains.com/idea)
* [Maven](https://maven.apache.org)

## Authors

List of [contributors](https://github.com/szczepanskikrs/Battleships/contributors) who participated in this project.

## License

This project is licensed under the Apache License, Version 2.0.

## Acknowledgments

* Hat tip to anyone who's was so bored to read all this stuff :)

## Additional bonuses from our mighty great leader Tomek!
* Two for Roberts scripts
* One for diagram drawing by Krzysztof
