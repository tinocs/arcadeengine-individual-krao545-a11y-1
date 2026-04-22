/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 21, 2026
 *
 * Is this lab fully working?
 */
package breakout;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Score extends Text
{
	private int value;
	
	public Score() 
	{
		value = 0;
		setFont(new Font(24));
		updateDisplay();
	}
	
	public void updateDisplay() 
	{
		setText("Score: " + value);
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
