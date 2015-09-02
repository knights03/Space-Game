package particle;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import game.Game;
import unit.Combatant;
import util.Calc;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import location.Location;

public class LaserBurst extends Projectile {
	private static final double SPEED = 1000;
	
	private Game game;

	public LaserBurst(Combatant source, Location direction, Game game) {
		
		this.game = game;
		
		setSpeed(SPEED);
		setSource(source);
		
		Shape sprite = new Circle(1, source.getEquipedLaserBlaster().getColor());
		setSprite(sprite);

		
		Location destination = Calc.instance.vectorDestination(source.getLocation(), direction,
				source.getEquipedLaserBlaster().getRange());
		
		
		double duration = source.getEquipedLaserBlaster().getRange()/getSpeed()*1000;
		
		setMovement(new TranslateTransition(Duration.millis(duration), getSprite()));
		
		getMovement().setFromX(source.getLocation().getX());
		getMovement().setFromY(source.getLocation().getY());
		
		getMovement().setToX(destination.getX());
		getMovement().setToY(destination.getY());
		
		setMovement(getMovement());
		
		//System.out.println(source.getEquipedLaserBlaster().getLaserDamage());
		
		getMovement().play();
		
		
		
		getMovement().setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				Group parent = (Group) getSprite().getParent();
				parent.getChildren().remove(getSprite());
				source.getFireList().remove(this);
				
			}
			
		});
		
		
		
		new Thread(collisionDetect(sprite)).start();
		
	}
	
	/**
	 * Creates a Task Thread that detects if the Laser Burst has collided with any combatants. While the Laser Burst
	 * animation is running it constantly cycles through all combatants in the game objects combatants list and checks
	 * if the Laser Burst's sprite boundaries have collided with any of the combatant's sprite boundaries. If a collision
	 * occurs hit() is called, creating a little explosion animation and combatant.laserHit() is called, executing
	 * whatever happens with a laser burst hits that combatant (in most cases, damage)
	 * 
	 * @param sprite The Laser Burst sprite
	 * @return The collision detection task to be run in the constructor
	 */
	private Task<Void> collisionDetect(Shape sprite) {
		Task<Void> collisionDetection = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				
				ArrayList<Combatant> combatantList = game.getCombatantList();
				while(getMovement().getStatus() == Animation.Status.RUNNING) {
					for(Combatant combatant : combatantList) {
						Bounds unitBound = combatant.getSprite().getBoundsInParent();
						if(unitBound.intersects(sprite.getBoundsInParent())) {
							hit();
							//System.out.println(LaserBurst.this.getSource().toString());
							combatant.laserHit(LaserBurst.this);
						}
					}
				}

				return null;
			}
			
		};
		
		return collisionDetection;
	}
	
	public void hit() {
		
		getMovement().stop();
		
		ScaleTransition explode = new ScaleTransition(Duration.millis(150), getSprite());
		explode.setByX(10.0);
		explode.setByY(10.0);
		explode.play();
		
		explode.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//Group parent = (Group) getSprite().getParent();
				//parent.getChildren().remove(getSprite());
				
				LaserBurst.this.game.removeNode(LaserBurst.this.getSprite());
				
			}
			
		});
	}
	

}
