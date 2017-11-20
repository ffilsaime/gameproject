package modelpack;
/**
 * This class is made to creat the background of the app.
 * @author flore
 *
 */
public class BackgroundClass {

	private int _bgX, _bgY, _speedX; // the x and y values represent the upper 
	//left corner
	public BackgroundClass(int x, int y){
		_bgX = x;
		_bgY = y;
		_speedX = 0;
	}
	
	/**
	 * This method will be used to infinitely scroll the background.
	 * @author flore
	 */
	public void update(){
		_bgX += _speedX; // changes the bgX speed in the x direction.
		if(_bgX<=-2160){
			_bgX +=4320;
		}
	}

	public int get_bgX() {
		return _bgX;
	}

	public int get_bgY() {
		return _bgY;
	}

	public int get_speedX() {
		return _speedX;
	}

	public void set_bgX(int _bgX) {
		this._bgX = _bgX;
	}

	public void set_bgY(int _bgY) {
		this._bgY = _bgY;
	}

	public void set_speedX(int _speedX) {
		this._speedX = _speedX;
	}
	
	
	
}
