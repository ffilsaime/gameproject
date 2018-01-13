package modelpack;
/**
 * This class will control the xy coordinates and speed,
 * make updates to speed and positon, and allow other classes to 
 * get the x and y variables 
 * @author flore
 *
 */
import java.awt.Graphics;

import kiloboltgame.StartingClass;

public class Robot {
	//In Java, Class Variables should be private so that only its methods can change them.
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382; 
	
	private int centerX = 100; // the x coordinates for the robot
	private int centerY = GROUND; // the y coordinates for the robot
	private boolean jumped = false; // changes to true if the character is in the air and false on the ground
	private boolean movingLeft = false; //changes to true if the character is walking to the left
	private boolean movingRight = false;//changes to true if the character is walking to the right
	private boolean ducked = false; //changes to true if the character is ducking
	
	private static BackgroundClass bg1 = StartingClass.getBg1();
	private static BackgroundClass bg2 = StartingClass.getBg2();
	private int speedX = 0; // the rate at which the x position changes
	private int speedY = 1; // the rate at which the y position changes

	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (speedX < 0) {
			centerX += speedX;// this changes center X by adding speedX.
		} if(speedX==0 || speedX < 0){
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		} if(centerX <= 200 && speedX > 0){
			centerX += speedX;
		} if(speedX > 0 && centerX > 200){
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		} 
		// Updates Y Positions
		centerY += speedY;
		if(centerY + speedY >= GROUND){
			centerY=GROUND;
		}
		//Handles jumping
		if(jumped == true){
			speedY += 1;
			if(centerY + speedY >= GROUND){
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}
		}
		// Prevents going beyond X coordinate of 0
		if(centerX + speedX <= 60){
			centerX = 61;
		}
		
	}
	
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	public void moveRight() {
		if(ducked == false){
			speedX = MOVESPEED;
		}
	}
	
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	public void moveLeft() {
		if(ducked == false){
			speedX = -MOVESPEED;
		}
	}
	
	// returns the truth value of the private variable ducked
	public boolean isDucked(){
		return ducked;
	}
	
	// returns the truth value of the private variable movingRight
	public boolean isMovingRight(){
		return movingRight;
	}
	
	public boolean isMovingLeft(){
		return movingLeft;
	}
	//changes the speedX value so the coordinate of the value can
	//change. It changes the x value of the coordinate
	private void stop() {
		if(isMovingRight()==false && isMovingLeft()== false){
			speedX = 0;
		}
		if(isMovingRight()== false && isMovingLeft()== false){
			moveLeft();
		}
		if(isMovingRight()== true && isMovingLeft()==false){
			moveRight();
		}
	}
	//changes the speedY value so the coordinate of the value can
	//change. It changes the Y value of the coordinate
	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}
	
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

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}
	
}