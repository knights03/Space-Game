package particle;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import location.Location;
import unit.Combatant;

public abstract class Projectile {

	private Node sprite;
	
	private Combatant source;
	private Location direction;
	
	private double speed;
	private double damage;
	
	private TranslateTransition movement;
	
	public Projectile() {
		
	}

	public Node getSprite() {
		return sprite;
	}

	public Combatant getSource() {
		return source;
	}

	public Location getDirection() {
		return direction;
	}

	public double getSpeed() {
		return speed;
	}

	public TranslateTransition getMovement() {
		return movement;
	}

	public void setSprite(Node sprite) {
		this.sprite = sprite;
	}

	public void setSource(Combatant source) {
		this.source = source;
	}

	public void setDirection(Location direction) {
		this.direction = direction;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setMovement(TranslateTransition movement) {
		this.movement = movement;
	}
}
