package engine;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

import engine.World;

/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 17, 2026
 *
 * Is this lab fully working? yes!! (I think)
 */


public abstract class Actor extends javafx.scene.image.ImageView {

	/**
	 * 
	 */
	public Actor() {
		super();
	}
	
	public abstract void act(long now); 
	
	public void move(double dx, double dy) 
	{
		setX(getX() + dx);
		setY(getY() + dy);
	}
	
	public World getWorld() 
	{
		return (World) getParent();
	}
	
	public double getWidth() 
	{
		return getBoundsInParent().getWidth();
	}
	
	public double getHeight() 
	{
		return getBoundsInParent().getHeight();
	}
	
	public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls)
	{
		List<A> result = new ArrayList<>();
		World world = getWorld();
		
		if(world == null) 
		{
			return result;
		}
		
		for(A actor : world.getObjects(cls)) 
		{
			if(actor != this && actor.getBoundsInParent().intersects(getBoundsInParent())) 
			{
				result.add(actor);
			}
		}
		return result;
	}
	
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) 
	{
		World world = getWorld();
		
		if(world == null) 
		{
			return null;
		}
		
		for(A actor : world.getObjects(cls)) 
		{
			if(actor != this && actor.getBoundsInParent().intersects(getBoundsInParent())) 
			{
				return actor;
			}
		}
		return null;
	}
	public void addedToWorld() 
	{
		
	}

}
