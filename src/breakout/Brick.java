/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;
import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor{

	public Brick() {
		String path = getClass().getClassLoader().getResource("breakoutresources/images/brick2.png").toString();
		Image img = new Image(path);
		setImage(img);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
