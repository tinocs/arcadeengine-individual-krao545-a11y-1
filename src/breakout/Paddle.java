/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Paddle extends Actor{

	/**
	 * 
	 */
	public Paddle()
	{
		String path = getClass().getClassLoader().getResource("resources/paddle.png").toString();
		Image img = new Image(path);
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	

}
