package kiloboltgame;

import java.applet.Applet;
import modelpack.Robot;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;

public class StartingClass extends Applet implements Runnable, KeyListener{
	private Robot _robot;
	private Image image, character;
	private Graphics second;
	private URL base;
	
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
		setFocusable(true); //when the game starts, the applet focuses and uses the 
		//user input
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");
		try{
			base = getDocumentBase();
		} catch(Exception e){
			//TODO: handle exception
		}
		character = getImage(base, "data/character.png");
	}

	@Override
	public void start() {
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
	 * forever
	 * @author flore
	 */
	@Override
	public void run() {
		while(true){
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
		g.drawImage(character,_robot.getCenterX()-61, _robot.getCenterY(), this);
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
			System.out.println("Move down");
			break;
		case KeyEvent.VK_LEFT:
			_robot.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			_robot.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			_robot.jump();
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP: // Constant for the up arrow key
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			_robot.stop();
			break;
		case KeyEvent.VK_RIGHT:
			_robot.stop();
			break;
		case KeyEvent.VK_SPACE:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
