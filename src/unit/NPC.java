package unit;

import java.util.ArrayList;

import faction.Faction;
import game.Dockable;
import game.Game;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.util.Duration;
import location.Coord;
import location.Location;
import particle.LaserBurst;
import particle.Projectile;
import ship.Ship;
import ship.ShipClass;
import util.Calc;
import weapon.LaserBlaster;

public class NPC implements Sprite, Combatant, Dockable {
	
	
	private String name;
	private Ship ship;
	
	private Faction faction;
	
	private Location location;
	private Location destination;
	private TranslateTransition movement = new TranslateTransition();
	
	private ArrayList<Projectile> fireList;
	
	public NPC(String name, ShipClass shipClass, Game game) {
		this.name = name;
		this.ship = new Ship(shipClass, game);
	}

	@Override
	public ArrayList<Projectile> getFireList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LaserBlaster getEquipedLaserBlaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void laserHit(LaserBurst laserBurst) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getSprite() {
		// TODO Auto-generated method stub
		return ship.getSprite();
	}

	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDiscovered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		this.location = location;
	}
	
	public void setRotation(double rotation) {
		ship.getSprite().setRotate(rotation);
	}

	public Location getDestination() {
		return destination;
	}

	public TranslateTransition getMovement() {
		return movement;
	}

	public void setDestination(Location destination) {

		this.destination = destination;

		if(movement.getStatus() == Animation.Status.RUNNING) {
			movement.stop();
		}

		this.run();
	}

	public void setMovement(TranslateTransition movement) {
		this.movement = movement;
	}
	
	
	public void run() {
		Task<Void> updateLocation = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while(movement.getStatus() == Animation.Status.RUNNING) {
					location.setX(ship.getSprite().getTranslateX());
					location.setY(ship.getSprite().getTranslateY());
				}
				return null;
			}
			
		};
		

		
		ship.getSprite().setRotate(Math.toDegrees(Calc.instance.getAngle(location, destination))-90);
		
		double xDistanceToDestination = Math.abs(location.getX()-destination.getX());
		double yDistanceToDestination = Math.abs(location.getY()-destination.getY());
		
		double distanceToDestination = Math.sqrt(Math.pow(xDistanceToDestination, 2)
				+ Math.pow(yDistanceToDestination, 2));
		
		double timeToDestination = distanceToDestination/ship.getSpeed()*1000;
		
		movement = new TranslateTransition(Duration.millis(timeToDestination), ship.getSprite());
		movement.setToX(destination.getX());
		movement.setToY(destination.getY());

		movement.play();
		
		new Thread(updateLocation).start();
	}

}
