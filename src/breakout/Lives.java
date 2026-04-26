/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 25, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lives extends Text {

	private int value;
	
	public Lives() {
		value = 3;
		setFont(new Font(25));
		updateDisplay();
	}

	public void updateDisplay() 
	{
		setText("Lives: " + value);
	}
	
	public int getValue() 
	{
		return value;
	}
	
	public void setValue(int value) 
	{
		this.value = value;
		updateDisplay();
	}
}
