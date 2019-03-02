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
			Image Mars = new Image("Mars.png");
			
			final long startTime = System.nanoTime();
			
			new AnimationTimer() 
			{
				double x = 112.0;
				double y = 112.0;
				
				@Override
				public void handle(long now) {
					double dd = (now-startTime)/1000000000.0;
					double mx = 232+100*Math.cos(dd);
					double my = 232+200*Math.sin(dd);
					gc.drawImage(space, 0, 0);
					if(x==400.0 && y<400.0)
					{
						y=y+1.0;
						gc.drawImage(earth, x, y);
						
					}
					if(y==400.0 && x>112.0)
					{
						x=x-1.0;
						gc.drawImage(earth, x, y);
					}
					if(x==112.0 && y>112.0)
					{
						y=y-1.0;
						gc.drawImage(earth, x, y);
					}
					if(x<400.0 && y==112.0)
					{
						x=x+1.0;
						gc.drawImage(earth, x, y);
					}
					System.out.print(x);
					System.out.println(y);
					gc.drawImage(Mars, mx, my);
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
