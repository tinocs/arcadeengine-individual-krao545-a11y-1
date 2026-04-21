/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import engine.World;

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
		
		//BallWorld:
		Paddle paddle = new Paddle();
		
		paddle.setX(getWidth() / 2 - 50);
		paddle.setY(getHeight() / 2 - 50);
		add(paddle);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
