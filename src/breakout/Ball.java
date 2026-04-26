package breakout;

import engine.Actor;
import engine.Sound;
import javafx.animation.FadeTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
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
		BallWorld world = (BallWorld) getWorld();
		if(world.isPaused()) 
		{
			return;
		}
		
		move(dx, dy);
		
		double worldWidth = getWorld().getWidth();
		double worldHeight = getWorld().getHeight();
		
		if(getX() <= 0 || getX() + getWidth() >= worldWidth) 
		{
			dx = -dx;
			Sound ballBounceSound = new Sound("ballbounceresources/ball_bounce.wav");
			ballBounceSound.play();
			
		}
		
		if(getY() <= 0) 
		{
			dy = -dy;
			Sound ballBounceSound = new Sound("ballbounceresources/ball_bounce.wav");
			ballBounceSound.play();
		}
		
		if(getY() + getHeight() >= worldHeight && dy > 0) 
		{
			world.loseLife();
			return;
		}
		
		Paddle paddle = getOneIntersectingObject(Paddle.class);
		if(paddle != null) 
		{
			dy = -dy;
		}
		
		Brick brick = getOneIntersectingObject(Brick.class);
		if(brick != null) 
		{
			world.getScore().setValue(world.getScore().getValue() + 100);
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
			
			FadeTransition fade = new FadeTransition(Duration.millis(200), brick);
			fade.setFromValue(1.0);
			fade.setToValue(0.0);
			
			/*fade.setOnFinished(e ->
			{
				getWorld().remove(brick);
			});
			*/
			
			fade.play();

			Sound brickHitSound = new Sound("ballbounceresources/brick_hit.wav");
			brickHitSound.play();
		}
	}

}
