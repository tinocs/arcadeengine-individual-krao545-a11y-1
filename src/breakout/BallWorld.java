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
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class BallWorld extends World{

	private Score score;
	private int level = 1;
	private int lives = 3;
	private boolean started = false;
	
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
		
		score = new Score();
		score.setX(10);
		score.setY(25);
		getChildren().add(score);
		
		loadLevel(level);
		
		setOnMouseClicked(e -> started = true);
	}
	
	public Score getScore() 
	{
		return score;
	}
	
	public boolean isStarted() 
	{
		return started;
	}
	
	public void loseLife() 
	{
		lives--;
		if(lives <= 0) 
		{
			Text message = new Text("GAME OVER");
			message.setStyle("-fx-font-size: 40px");
			message.setX(200);
			message.setY(200);
			getChildren().add(message);
			
			setOnMouseClicked(e -> Breakout.showTitleScreen());
			stop();
		}
		resetBall();
		started = false;
	}

	public void resetBall()
	{
		for(Actor a : getObjects(Ball.class)) 
		{
			remove(a);
		}
		
		Ball ball = new Ball();
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		add(ball);
	}
	
	public void loadLevel(int level) 
	{
		clearBricks();
		
		if(level == 1) 
		{
			createGrid(3, 7);
		}
		else if(level == 2) 
		{
			createGrid(5, 5);
		}
		
		started = false;
	}
	
	public void clearBricks() 
	{
		for(Actor b : getObjects(Brick.class)) 
		{
			remove(b);
		}
	}
	
	public void createGrid(int rows, int cols) 
	{
		int brickWidth = 50;
		int brickHeight = 20;
		
		for(int row = 0; row < rows; row++) 
		{
			for(int col = 0; col < cols; col++) 
			{
				Brick brick = new Brick();
				
				brick.setX(50 + col * brickWidth);
				brick.setY(50 + row * brickHeight);
				
				add(brick);
			}
		}
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(getObjects(Brick.class).isEmpty()) 
		{
			level++;
			resetBall();
			if(level > 2) 
			{
				stop();
				System.out.println("YOU WIN!! game over");
				Breakout.showTitleScreen();
			}
			else 
			{
				loadLevel(level);
			}
		}
	}

}
