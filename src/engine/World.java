/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 17, 2026
 *
 * Is this lab fully working? yes!! (I hope)
 */
package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends javafx.scene.layout.Pane {

	private AnimationTimer timer;
	private boolean stopped;
	
	private Set<KeyCode> keysPressed;
	
	private boolean widthInitialized;
	private boolean heightInitialized;
	private boolean dimensionsInitializedCalled;
	
	public World() 
	{
		keysPressed = new HashSet<>();
		stopped = true;
		widthInitialized = false;
		heightInitialized = false;
		dimensionsInitializedCalled = false;
		
		widthProperty().addListener((obs, oldVal, newVal) ->
		{
			if(!widthInitialized && newVal.doubleValue() > 0) 
			{
				widthInitialized = true;
				checkDimensionsInitialized();
			}
		});
		
		heightProperty().addListener((obs, oldVal, newVal) ->
		{
			if(!heightInitialized && newVal.doubleValue() > 0) 
			{
				heightInitialized = true;
				checkDimensionsInitialized();
			}
		});
		
		sceneProperty().addListener((obs, oldScene, newScene) ->
		{
			if(newScene != null) 
			{
				requestFocus();
			}
		});
		
		setOnKeyPressed(e -> keysPressed.add(e.getCode()));
		setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
		
		timer = new AnimationTimer() 
		{
			@Override
			public void handle(long now) 
			{
				act(now);
				
				List<Actor> actors = getObjects(Actor.class);
				for(Actor actor : actors) 
				{
					if(actor.getWorld() == World.this) 
					{
						actor.act(now);
					}
				}
			}
		};
	}
	
	private void checkDimensionsInitialized() 
	{
		if(widthInitialized && heightInitialized && !dimensionsInitializedCalled) 
		{
			dimensionsInitializedCalled = true;
			onDimensionsInitialized();
		}
	}
	
	public abstract void onDimensionsInitialized();
	public abstract void act(long now);
	
	public void start() 
	{
		if(stopped) 
		{
			timer.start();
			stopped = false;
		}
	}
	
	public void stop() 
	{
		if(!stopped) 
		{
			timer.stop();
			stopped = true;
		}
	}
	
	public boolean isStopped() 
	{
		return stopped;
	}
	
	public void add(Actor actor) 
	{
		getChildren().add(actor);
		actor.addedToWorld();
	}
	
	public void remove(Actor actor) 
	{
		getChildren().remove(actor);
	}

	public <A extends Actor> List<A> getObjects(Class<A> cls)
	{
		List<A> result = new ArrayList<>();
		
		for(javafx.scene.Node node : getChildren()) 
		{
			if(cls.isInstance(node)) 
			{
				result.add(cls.cast(node));
			}
		}
		return result;
	}
	
	public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls)
	{
		List<A> result = new ArrayList<>();
		
		for(A actor : getObjects(cls)) 
		{
			if(actor.getBoundsInParent().contains(x, y)) 
			{
				result.add(actor);
			}
		}
		return result;
	}
	
	public boolean isKeyPressed(KeyCode code) 
	{
		return keysPressed.contains(code);
	}
	

}
