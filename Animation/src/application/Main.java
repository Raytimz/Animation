package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Animation");
			Group root = new Group();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			Canvas canvas = new Canvas(512,512);
			root.getChildren().add(canvas);
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			Image sun = new Image("sun.png");
			Image earth = new Image("earth.png");
			Image space = new Image("space.png");
			
			final long startTime = System.nanoTime();
			
			new AnimationTimer() 
			{
				double x = 100;
				double y = 100;
				@Override
				public void handle(long now) {
					double dd = (now-startTime)/1000000000.0;
					//double x = 232+100*Math.cos(dd);
					//double y = 232+200*Math.sin(dd);
					
					if(x==100 && y<100)
					{
						y=y+1;
						gc.drawImage(earth, x, y);
					}
					if(y==100 && x<100)
					{
						x=x-1;
						gc.drawImage(earth, x, y);
					}
					if(x==100 && y>0)
					{
						y=y-1;
						gc.drawImage(earth, x, y);
					}
					if(x>0 && y==100)
					{
						x=x-1;
						gc.drawImage(earth, x, y);
					}
					
					gc.drawImage(space, 0, 0);
					gc.drawImage(sun, 196, 196);
					
				}
				
			}.start();
			
			//gc.drawImage(sun, 200, 200);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
