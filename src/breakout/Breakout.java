/**
 * Kavita Rao
 * Period 1, APCS
 * Date:Apr 20, 2026
 *
 * Is this lab fully working?
 */
package breakout;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Breakout extends Application {

	public static Stage mainStage;
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		mainStage = stage;
		stage.setTitle("Breakout");
		showTitleScreen();
	}

	public static void showTitleScreen() 
	{
		
		VBox root = new VBox(20);
		root.setStyle("-fx-alignment: center;");
		
		Text title = new Text("Breakout");
		title.setStyle("-fx-font-size: 50px;");
		
		Button playButton = new Button("Play");
		
		playButton.setOnAction(e -> 
		{
			startGame();
		});
		
		root.getChildren().addAll(title, playButton);
		
		Scene scene = new Scene(root, 600, 400);
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	public static void startGame() 
	{
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		root.setCenter(world);
		Scene scene = new Scene(root, 600, 400);
	
		mainStage.setScene(scene);
		world.start();
	}

}
