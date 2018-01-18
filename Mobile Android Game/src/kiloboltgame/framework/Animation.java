package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

/**
 * This class will add a frame to a new animation, update the current
 * and frame with the appropriate image
 * @author flore
 *
 */
public class Animation {
	private ArrayList frames;
	private int currentFrame;//the index of the current image displayed
	/*
	 * animeTime will measure the amount of time which an image has been 
	 * displayed. Then when total duration is 400 it would move to the next 
	 * AnimeFrame
	 */
	private long animTime, totalDuration;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		
		synchronized(this){
			animTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void addFrame(Image image,long duration){
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	/*
	 * This will switch frames when necessary.
	 */
	public synchronized void update(long elapsedTime){
		if(frames.size()>1){
			animTime += elapsedTime;
			if(animTime>=totalDuration){
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			while(animTime>getFrame(currentFrame).endTime){
				currentFrame = currentFrame + 1;
			}
		}
	}
	
	public synchronized Image getImage(){
		if(frames.size()==0){
			return null;
		}
		else{
			return getFrame(currentFrame).image;
		}
	}
	
	private AnimFrame getFrame(int i){
		return (AnimFrame)frames.get(i);
	}
/**
 * this will create an object that will contain the duration and image that would
 * need to be displayed.
 * @author flore
 *
 */
	private class AnimFrame{
		Image image;
		long endTime;
		
		public AnimFrame(Image pic, long endTime2){
			this.image = pic;
			this.endTime = endTime2;
		}
	}
}
