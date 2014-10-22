#AsteroidsProject
The long Assingment description is [here](http://bc-cisc3120-f14.github.io/project1-asteroids).

## Class and Method Summary

### AsteroidGame
####Init()
Fills in the background of our applet and draws needed shapes/objects.
#### Paint()
Sets up graphics
####KeyPressed/Released()
Gets the user input to move ship



### Ship
#### Draw()
Draws the components as they move.
#### Move()
Handles the ship of movements in game.
#### SetAccelerating()
Handles the use of the booster
####SetTurningLeft/Right()
Hanldes angluar movement of ship
#### Get x/y()
Get the ships location , x and y points during running.
#### Get Raidus()
Gets and keeps ships raidus

### AsteroidItSelf
#### AsteroidIts()
Gives contructor, formuala for direction and velocity.

#### Move()
Moves the asteroid and allows for wrap around when 
going beyong applet Dimensions.

#### Draw()
Draws and fills in an Asteroid

#### ShipCollision()
Checks to see if the asteroid and Ship has collided.
## Works cited
(https://help.github.com/articles/markdown-basics/)
(https://www.youtube.com/watch?v=6XoVf4x-tag)
(http://zetcode.com/tutorials/javagamestutorial/)

## Extra Credit Beahvior
Ship has a yellow flame as the booster that represents accelerration.
