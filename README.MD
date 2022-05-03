# BATTLESHIPS - Java Console Implementation #

## 1. Technology used ##

* Spring Shell
* Java 11
* Spring Boot

## 2. To build project ##

```
mvn package
```

## 3. To run ##

```
    java -jar bs-game-1.0.0.jar
```

## 4. How to play? ##

Run game and write down the CLI: `N` - this will run the game.

Add first coordinate for your Battleship - sample: `B2` and hit **ENTER** 

Add second coordinate for your Battleship - sample: `B6` and hit **ENTER**

Ship will contain width - `B2, B3, B4, B5, B6` -> `5 fields`

DO THE SAME FOR THE REST OF THE SHIPS REQUESTED BY THE GAME

When all set put some guess to shot computer ship -> e.g `A1`

The result should look like this:

```
    1  2  3  4  5  6  7  8  9  10 
A |???~~~~~~~~~~~~~~~~~~~~~~~~~~~|
B |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

    1  2  3  4  5  6  7  8  9  10 
A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
B |~~~ooooooooooooooo~~~~~~~~~~~~|
C |~~~oooooooooooo~~~~~~~~~~~~~~~|
D |~~~oooooooooooo~~~~~~~~~~~~~~~|
E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
```

First board is your enemy ship map. Second board is your map.

`xxx` -means that your ship was hit or you hit your enemy (depends on board)

`ooo` - means your part of ship (not hit not missed)

`???` - means that you missed the shot
