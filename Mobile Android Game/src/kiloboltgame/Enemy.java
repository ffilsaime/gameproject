package kiloboltgame;

import modelpack.BackgroundClass;
/**
 * This is the superclass for most enemy types.
 * It will contain behavior related methods.
 * They will most likely have max health, current
 * health, attack power, speed, and X and Y coordinate
 * @author flore
 *
 */
public class Enemy {
	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private BackgroundClass bg = StartingClass.getBg1();
	
	public void update(){
		centerX += speedX;
		speedX = bg.getSpeedX();
	}
	
	public void die(){
		
	}
	
	public void attack(){
		
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public BackgroundClass getBg() {
		return bg;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(BackgroundClass bg) {
		this.bg = bg;
	}
	
	
}
