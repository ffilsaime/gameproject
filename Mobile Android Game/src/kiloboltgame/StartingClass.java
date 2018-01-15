package kiloboltgame;

import java.applet.Applet;
import modelpack.BackgroundClass;
import modelpack.Robot;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Image;

public class StartingClass extends Applet implements Runnable, KeyListener{
	private Robot _robot;
	private Heliboy hb, hb2;
	private Image image, character, background, currentSprite, characterDown, characterJumped, heliboy;
	private Graphics second;
	private URL base;
	private static BackgroundClass _bg1, _bg2;

	public StartingClass(){

	}
	/**
	 * When the applet runs for the first time, it will run on this 
	 * method. It will define the certain parameters for the applet such
	 * as size, background color, and applet title.
	 * @author flore
	 */
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true); //when the game starts, the applet focuses and uses the user input
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try{
			base = getDocumentBase();
		} catch(Exception e){
			//TODO: handle exception
		}
		character = getImage(base, "data/character.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");
		currentSprite = character; 
		background = getImage(base,"data/background.png");
		heliboy = getImage(base,"data/heliboy.png");
	}
	/**
	 * The background, enemies and main character are being
	 * created.
	 * @author flore
	 */
	@Override
	public void start() {
		_bg1 = new BackgroundClass(0,0);
		_bg2 = new BackgroundClass(2160,0);
		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);
		_robot = new Robot();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	/**
	 * This method is to make the game loop. This will make it run
	 * forever. This will also check the state of the robot and make changes
	 * to the sprite
	 * @author flore
	 */
	@Override
	public void run() {
		while(true){
			_robot.update();
			if(_robot.isJumped()){
				currentSprite = characterJumped;
			}else if(_robot.isJumped()== false && _robot.isDucked()==false){
				currentSprite = character;
			}
			
			ArrayList projectiles = _robot.getProjectiles();
			for(int i = 0; i<projectiles.size();i++){
				Projectile p = (Projectile) prokectiles.get(i);
				if(p.isVisible()==true){
					p.update();
				}else{
					projectiles.remove(i);
				}
			}
			
			hb.update();
			hb2.update();
			_bg1.update();
			_bg2.update();
			repaint();// this calls paint
			try{
				Thread.sleep(17);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is used for double buffering.
	 * Double buffering is used to prevent tearing and flickering
	 * So it will remember the previous position of the robot for a 
	 * short amount of time so the robot moving looks natural
	 * @author flore
	 */
	@Override
	public void update(Graphics g){
		if(image == null){
			image= createImage(this.getWidth(),this.getHeight());
			second = image.getGraphics();
		}
		second.setColor(getBackground());;
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	public void paint(Graphics g){
		g.drawImage(background, _bg1.get_bgX(), _bg1.get_bgY(), this);
		g.drawImage(background, _bg2.get_bgX(), _bg2.get_bgY(), this);
		
		ArrayList projectiles = _robot.getProjectiles();
		for (int i= 0; i<projectiles.size(); i++){
			Projectile p = (Projectile)projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(),p.getY(), 10, 5);
		}
		
		g.drawImage(currentSprite,_robot.getCenterX()-61, _robot.getCenterY()-63, this);
		g.drawImage(heliboy, hb.getCenterX()-48, hb.getCenterY()-48, this);
		g.drawImage(heliboy, hb2.getCenterX()-48, hb2.getCenterY()-48, this);
	}

	/**
	 * This is used to create the buttons we are using to move the player
	 * @author flore
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			// Constant for the up arrow key
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if(_robot.isJumped()==false){
				_robot.setDucked(true);
				_robot.setSpeedX(0);
			}
			break;

		case KeyEvent.VK_LEFT:
			_robot.moveLeft();
			_robot.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			_robot.moveRight();
			_robot.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			_robot.jump();
			break;
		
		case KeyEvent.VK_CONTROL:
			if(_robot.isDucked()==false&& _robot.isJumped()==false){
				_robot.shoot();
			}
			break; }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP: // Constant for the up arrow key
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = character;
			_robot.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			_robot.stopLeft();
			System.out.println("Stop moving left");
			break;

		case KeyEvent.VK_RIGHT:
			_robot.stopRight();
			System.out.println("Stop moving right");
			break;

		case KeyEvent.VK_SPACE:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static BackgroundClass getBg1(){
		return _bg1;
	}

	public static BackgroundClass getBg2(){
		return _bg2;
	}

}
