package game;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MovementMarker {

	private Circle marker;
	private ScaleTransition fade;
	
	public MovementMarker(double x, double y, Game game) {
		marker = new Circle(2);
		marker.setStroke(Color.WHITE);
		marker.setFill(Color.TRANSPARENT);
		marker.setTranslateX(x);
		marker.setTranslateY(y);
		
		fade = new ScaleTransition(Duration.millis(500), marker);
		fade.setByX(4);
		fade.setByY(4);
		
		fade.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				game.removeNode(marker);
			}
			
		});
		
		fade.play();
	}
	
	public Circle getMarker() {
		return marker;
	}
}
