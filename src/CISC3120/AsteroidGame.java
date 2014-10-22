import java.applet.*; 
import java.awt.*; 
import java.awt.event.*; 
/**
 * Main Program Asteroids Initial Setup
 * @author Jonathan
 *Creates Motionless ships and Asteroids
 */
public class AsteroidsGame extends Applet implements Runnable, KeyListener{ 
Thread thread; 
long startTime, endTime, framePeriod; //long is just a very big integer
Dimension dim; // stores the size of the back buffer
Image img; // the back buffer object
Graphics g; // used to draw on the back buffer
AsteroidShip ship;
boolean paused;
AsteroidItSelf[] asteroid;
public void init(){ 
this.resize(700,700);
ship=new AsteroidShip(350,350,0,.35,.98,.1);
startTime=0; 
endTime=0; 
framePeriod=25; 
addKeyListener(this);// tells the class to listen for key events
dim=getSize(); //set dim equal to the size of the applet
img=createImage(dim.width, dim.height);//create the back buffer
g=img.getGraphics(); //retrieve Graphics object for back buffer
thread=new Thread(this); 
thread.start(); 
} 
/**
 * Graphics Method called, fills in background
 * and draws our objects.
 */
public void paint(Graphics gfx){ 
g.setColor(Color.black); //Notice these first four lines all
g.fillRect(0,0,700,700); //use g instead of gfx now. g draws
ship.draw(g);
gfx.drawImage(img,0,0,this); //copies back buffer to the screen
} 	
/**
 * Setting up applet to run
 */
public void run(){ 
 for(;;){//this infinite loop ends when the webpage is exited
startTime=System.currentTimeMillis(); 
if(!paused) 
ship.move(dim.width,dim.height); //move the ship
repaint(); 
try{ 
endTime=System.currentTimeMillis(); 
if(framePeriod-(endTime-startTime)>0) 
Thread.sleep(framePeriod-(endTime-startTime)); 
}catch(InterruptedException e){ 
} 
} 
} 
/**
 * Gets the user input to move ship.
 */
public void keyPressed(KeyEvent e){ 
if(e.getKeyCode()==KeyEvent.VK_ENTER){ 
//These first two lines allow the asteroids to move
//while the player chooses when to enter the game.
//This happens when the player is starting a new life.
if(!ship.isActive() && !paused) 
ship.setActive(true); 
else{ 
paused=!paused; //enter is the pause button
if(paused) // grays out the ship if paused
ship.setActive(false); 
else
ship.setActive(true); 
}
}	
else if(paused || !ship.isActive()) //if the game is 
return; //paused or ship is inactive, do not respond
//to the controls except for enter to unpause
else if(e.getKeyCode()==KeyEvent.VK_UP) 
ship.setAccelerating(true); 
else if(e.getKeyCode()==KeyEvent.VK_LEFT) 
ship.setTurningLeft(true); 
else if(e.getKeyCode()==KeyEvent.VK_RIGHT) 
ship.setTurningRight(true); 
} 

public void keyReleased(KeyEvent e){ 
if(e.getKeyCode()==KeyEvent.VK_UP) 
ship.setAccelerating(false); 
else if(e.getKeyCode()==KeyEvent.VK_LEFT) 
ship.setTurningLeft(false); 
else if(e.getKeyCode()==KeyEvent.VK_RIGHT) 
ship.setTurningRight(false);
} 
public void keyTyped(KeyEvent e){ //empty method, but still needed to 
} //implement the KeyListener interface
}
