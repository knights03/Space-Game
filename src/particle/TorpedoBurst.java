package particle;

import java.awt.event.ActionListener;

import unit.Combatant;
import util.Calc;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import location.Location;

public class TorpedoBurst extends Projectile {
	private static final double SPEED = 300;
	private static final double DISTANCE = 300;

	public TorpedoBurst(Combatant source, Location target) {
		
		
		
		setSpeed(SPEED);
		
		Shape sprite = new Circle(2, Color.BLUE);
		setSprite(sprite);

		Location destination;
		
		if(Calc.instance.getDistance(source.getLocation().getX(), source.getLocation().getY(),
				target.getX(), target.getY()) > DISTANCE) {
			destination = Calc.instance.vectorDestination(source.getLocation(), target, DISTANCE);
		} else {
			destination = target;
		}
		
		
		
		double duration = DISTANCE/getSpeed()*1000;
		
		setMovement(new TranslateTransition(Duration.millis(duration), getSprite()));
		
		getMovement().setFromX(source.getLocation().getX());
		getMovement().setFromY(source.getLocation().getY());
		
		getMovement().setToX(destination.getX());
		getMovement().setToY(destination.getY());
		
		setMovement(getMovement());
		
		
		getMovement().play();
		
		
		
		getMovement().setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hit();
				//Group parent = (Group) getSprite().getParent();
				//parent.getChildren().remove(getSprite());
				
			}
			
		});
		
		
		
	}
	
	public void hit() {
		
		getMovement().stop();
		
		ScaleTransition explode = new ScaleTransition(Duration.millis(150), getSprite());
		explode.setByX(15.0);
		explode.setByY(15.0);
		explode.play();
		
		explode.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Group parent = (Group) getSprite().getParent();
				parent.getChildren().remove(getSprite());
				
			}
			
		});
	}
	

}
