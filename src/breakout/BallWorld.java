/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import engine.Actor;
import engine.Sound;
import engine.World;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

public class BallWorld extends World{

	private Score score;
	private int level = 1;
	private int lives = 3;
	private boolean isPaused = true;
	private boolean isGameOver = false;
	
	private Lives livesDisplay; 
	
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
		
		livesDisplay = new Lives();
		livesDisplay.setX(500);
		livesDisplay.setY(25);
		getChildren().add(livesDisplay);
		
		loadLevel(level);
		
		setOnKeyPressed(e ->
		{
			if(e.getCode() == KeyCode.SPACE) 
			{
				if(isGameOver) 
				{
					Breakout.showTitleScreen();
				}
				else 
				{
					setPaused(false);
				}
			}
		});
	}
	
	public Score getScore() 
	{
		return score;
	}
	
	public boolean isPaused() 
	{
		return isPaused;
	}
	
	public void setPaused(boolean value) 
	{
		isPaused = value;
	}
	
	public void loseLife() 
	{
		lives--;
		
		livesDisplay.setValue(lives);
		
		Sound loseLifeSound = new Sound("ballbounceresources/lose_life.wav");
		loseLifeSound.play();
		
		if(lives <= 0) 
		{
			Sound gameOverSound = new Sound("ballbounceresources/game_lost.wav");
			gameOverSound.play();
			
			Text message = new Text("GAME OVER: \n  YOU LOST \nPress SPACE ");
			message.setStyle("-fx-font-size: 40px");
			message.setX(200);
			message.setY(200);
			getChildren().add(message);
			
			setPaused(true);
			isGameOver = true;
			
			return;
		}
		resetBall();
		setPaused(true);
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
		
		setPaused(true);
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
		if(isPaused()) 
		{
			return;
		}
		
		if(getObjects(Brick.class).isEmpty()) 
		{
			level++;
			resetBall();
			if(level > 2) 
			{
				
				
				Sound gameWonSound = new Sound("ballbounceresources/game_won.wav");
				gameWonSound.play();
				Text win = new Text("YOU WIN!! \nPress SPACE");
				win.setStyle("-fx-font-size: 40px");
				win.setX(200);
				win.setY(200);
				getChildren().add(win);
				
				setPaused(true);
				isGameOver = true;
			}
			else 
			{
				loadLevel(level);
			}
		}

	}

}

