/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import engine.Actor;
import engine.World;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor{

	/**
	 * 
	 */
	public Paddle()
	{
		String path = getClass().getClassLoader().getResource("breakoutresources/images/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		World world = getWorld();
		
		if(world.isKeyPressed(KeyCode.LEFT)) 
		{
			setX(getX() - 5);
			((BallWorld) world).scroll(-5);
		}
		
		if(world.isKeyPressed(KeyCode.RIGHT)) 
		{
			setX(getX() + 5);
			((BallWorld) world).scroll(5);
		}
	
		
	}
	
	

}
