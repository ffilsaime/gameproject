package modelpack;
/**
 * This class will control the xy coordinates and speed,
 * make updates to speed and positon, and allow other classes to 
 * get the x and y variables 
 * @author flore
 *
 */
import java.awt.Graphics;

public class Robot {
	//In Java, Class Variables should be private so that only its methods can change them.
	private int centerX = 100; // the x coordinates for the robot
	private int centerY = 382; // the y coordinates for the robot
	private boolean jumped = false; // changes to true if the character is in the air and false on
	// the ground

	private int speedX = 0; // the rate at which the x position changes
	private int speedY = 1; // the rate at which the y position changes

	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public boolean isJumped() {
		return jumped;
	}
	public int getSpeedX() {
		return speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			centerX += speedX;// this changes center X by adding speedX.
		} else if (speedX == 0) {
			System.out.println("Do not scroll the background.");

		} else {
			if (centerX <= 150) {//if the character's center X is in the left 150 pixels
				centerX += speedX;//Changes centerX by adding speedX
			} else {
				System.out.println("Scroll Background Here");
			}
		}
		// Updates Y Position. 382 is where the character's centerY would be if he were 
		//standing on the ground.
		if (centerY + speedY >= 382) {
			centerY = 382;}
		else{                       
			centerY += speedY;//changes y value
		}
		// Handles Jumping
		if (jumped == true) {
			speedY += 1;// makes the character seem like they are jumping
			//382 is the y value that would represent walking on the ground
			//NOTE: This will bring the character downwards
			if (centerY + speedY >= 382) {
				centerY = 382;
				speedY = 0;
				jumped = false;
			}
		}
		// Prevents going beyond X coordinate of 0 //If speedX plus centerX would bring the 
		//character outside the screen,
		//Fix the character's centerX at 60 pixels.
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
	}
	
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	public void moveRight() {
		speedX = 6;
	}
	
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	public void moveLeft() {
		speedX = -6;
	}
	
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	public void stop() {
		speedX = 0;
	}
	//changes the speedY value so the coordinate of the value can
	//change. It changes the Y value of the coordinate
	public void jump() {
		if (jumped == false) {
			speedY = -15;
			jumped = true;
		}

	}
}