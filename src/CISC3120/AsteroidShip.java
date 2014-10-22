import java.awt.*; 
/**
 * This Class defines the shape of ship and Booster
 * @author Jonathan Student of BCNY
 * CISC3120
 */
public class AsteroidShip { 
final double[] origXPts={14,-10,-6,-10},origYPts={0,-8,0,8}, 
origFlameXPts={-6,-23,-6},origFlameYPts={-3,0,3}; 
final int radius=6; // radius of circle used to approximate the ship 
double x, y, angle, xVelocity, yVelocity, acceleration, 
 velocityDecay, rotationalSpeed; //variables used in movement
boolean turningLeft, turningRight, accelerating, active; 
int[] xPts, yPts, flameXPts, flameYPts; //store the current locations 
//of the points used to draw the ship and its flame
int shotDelay, shotDelayLeft; //used to determine the rate of firing
/**
 * Constructor asteroid ship 
 * Sets up General/Start position for ship
 * @param x
 * @param y 
 * @param angle
 * @param acceleration
 * @param velocityDecay
 * @param rotationalSpeed
 */
public AsteroidShip(double x, double y, double angle, double acceleration, 
double velocityDecay, double rotationalSpeed){ 
this.x=x; 
this.y=y; 
this.angle=angle; 
this.acceleration=acceleration; 
this.velocityDecay=velocityDecay; 
this.rotationalSpeed=rotationalSpeed; 
xVelocity=0; // always in a motionless state to being
yVelocity=0; 
turningLeft=false; 
turningRight=false;
accelerating=false; 
active=false; // start off paused
xPts=new int[4]; // sets space for arrays
yPts=new int[4]; 
flameXPts=new int[3]; 
flameYPts=new int[3]; 
} 
/**
 * Draws our objects (ship and booster)
 * @param g
 *Fills in ship, booster, makes sure that booster
 *is always connected to the back of ship
 */
public void draw(Graphics g){ 
//rotate the points, translate them to the ship's location (by
//adding x and y), then round them by adding .5 and casting them
//as integers (which truncates any decimal place) 
if(accelerating && active){ // draw flame if accelerating
for(int i=0;i<3;i++){ 
flameXPts[i]=(int)(origFlameXPts[i]*Math.cos(angle)- 
origFlameYPts[i]*Math.sin(angle)+x+.5); 
flameYPts[i]=(int)(origFlameXPts[i]*Math.sin(angle)+ origFlameYPts[i]*Math.cos(angle)+y+.5); 
} 
g.setColor(Color.yellow); //set color of flame/booster
g.fillPolygon(flameXPts,flameYPts,3); // 3 is # of points
} 
//calculate the polygon, then draw it
for(int i=0;i<4;i++){ 
xPts[i]=(int)(origXPts[i]*Math.cos(angle)- //rotate 
origYPts[i]*Math.sin(angle)+x+.5); //translate and round
yPts[i]=(int)(origXPts[i]*Math.sin(angle)+ //rotate
origYPts[i]*Math.cos(angle)+y+.5); //translate and round
} 
if(active) // active means game is running (not paused)
g.setColor(Color.white); 
else // draw the ship dark gray if the game is paused
g.setColor(Color.darkGray); 
g.fillPolygon(xPts,yPts,4); // 4 is the number of points
}
/**
 * Handles ships' movement
 * @param scrnWidth
 * @param scrnHeight
 * Make sure that ship never disappears from the screen
 */
public void move(int scrnWidth, int scrnHeight){ 
if(turningLeft) //this is backwards from typical polar coordinates
angle-=rotationalSpeed; //because positive y is downward.
if(turningRight) //Because of that, adding to the angle is
angle+=rotationalSpeed; //rotating clockwise (to the right)
if(angle>(2*Math.PI)) //Keep angle within bounds of 0 to 2*PI
angle-=(2*Math.PI); 
else if(angle<0) 
angle+=(2*Math.PI); 
if(accelerating){ //adds accelerating velocity in direction pointed
xVelocity+=acceleration*Math.cos(angle); 
yVelocity+=acceleration*Math.sin(angle); 
} 
x+=xVelocity; //move the ship by adding velocity to position
y+=yVelocity; 
xVelocity*=velocityDecay; //slows ship down by percentages (velDecay
yVelocity*=velocityDecay; //should be a decimal between 0 and 1 
if(x<0) //wrap the ship around to the opposite side of the screen
x+=scrnWidth; //when it goes out of the screen's bounds
else if(x>scrnWidth) 
x-=scrnWidth; 
if(y<0) 
y+=scrnHeight; 
else if(y>scrnHeight) 
y-=scrnHeight;
}
/**
 * Handles use of booster
 * @param accelerating
 */
public void setAccelerating(boolean accelerating){ 
this.accelerating=accelerating; //start or stop accelerating the ship
} 
/**
 * Handles the left movement
 * @param turningLeft
 */
public void setTurningLeft(boolean turningLeft){ 
this.turningLeft=turningLeft; //start or stop turning the ship
} 
/**
 * Handles right movement
 * @param turningRight
 */
public void setTurningRight(boolean turningRight){ 
this.turningRight=turningRight; 
} 
/**
 * Get the ships location
 * @return with respect to the ships x co-ordinate
 */
public double getX(){ 
return x; // returns the ship’s x location
} 
/**
 * Gets ships y point
 * @return ships location with respect to y
 */
public double getY(){ 
return y; 
} 
/**
 * 
 * @return radius of the ship
 */
public double getRadius(){ 
return radius; // returns radius that approximates the ship
} 
public void setActive(boolean active){ 
this.active=active; //used when the game is paused or unpaused
} 
/**
 *  
 * @return state of the game
 */
public boolean isActive(){ 
return active; 
} 
}
