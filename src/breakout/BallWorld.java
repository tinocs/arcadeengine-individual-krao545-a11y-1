/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BallWorld extends World{

	/**
	 * 
	 */
	public BallWorld() {
		setPrefSize(600, 400);
	}

	@Override
	public void onDimensionsInitialized() {
		// TODO Auto-generated method stub
		Ball ball = new Ball();
		
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		ball.setX(centerX);
		ball.setY(centerY);
		add(ball);
		
		Paddle paddle = new Paddle();
		
		paddle.setX(centerX);
		paddle.setY(centerY + 175);
		add(paddle);
		
		setOnMouseMoved(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event) {
				paddle.setX(event.getX() - paddle.getWidth() / 2);
				
			}
			
		});
		
		int rows = 3;
		int cols = 7;
		int brickWidth = 50;
		int brickHeight = 20;
		
		for(int row = 0; row < rows; row++) 
		{
			for(int col = 0; col < cols; col++) 
			{
				Brick brick = new Brick();
				
				brick.setX(50 + col * brickWidth);
				brick.setY(50 + col * brickHeight);
				
				add(brick);
			}
		}
		
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
