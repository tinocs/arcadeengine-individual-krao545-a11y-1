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
		
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
