package breakout;

import engine.Actor;
import javafx.scene.image.Image;
/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 18, 2026
 *
 * Is this lab fully working?
 */


public class Ball extends Actor{
	
	private double dx;
	private double dy;
	
	public Ball()
	{
		String path = getClass().getClassLoader().getResource("breakoutresources/images/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		
		dx = 5;
		dy = 5;
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		move(dx, dy);
		
		double worldWidth = getWorld().getWidth();
		double worldHeight = getWorld().getHeight();
		
		if(getX() <= 0 || getX() + getWidth() >= worldWidth) 
		{
			dx = -dx;
		}
		
		if(getY() <= 0 || getY() + getHeight() >= worldHeight) 
		{
			dy = -dy;
		}
		
		Paddle paddle = getOneIntersectingObject(Paddle.class);
		if(paddle != null) 
		{
			dy = -dy;
		}
		
		Brick brick = getOneIntersectingObject(Brick.class);
		if(brick != null) 
		{
			if(getX() >= brick.getX() && getX() <= brick.getX() + brick.getWidth()) 
			{
				dy = -dy;
			}
			else if(getY() >= brick.getY() && getY() <= brick.getY() + brick.getHeight()) 
			{
				dx = -dx;
			}
			else 
			{
				dx = -dx;
				dy = -dy;
			}
			
			getWorld().remove(brick);
		}
	}

}
