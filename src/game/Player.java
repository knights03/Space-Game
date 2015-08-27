package game;

import java.util.ArrayList;

import cargo.NotEnoughSpace;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import location.Coord;
import location.Location;
import mining.MiningTool;
import particle.LaserBurst;
import particle.Projectile;
import ship.Ship;
import ship.ShipClasses;
import unit.Combatant;
import unit.Sprite;
import util.Calc;
import util.RandomGenerator;
import weapon.LaserBlaster;

public class Player implements Sprite, Combatant {
	private String name;
	private double speed;
	
	private Game game;

	private Location location;
	private Location destination;
	private TranslateTransition movement = new TranslateTransition();
	
	private Polygon sprite;
	
	private double laserDamage;
	private double criticalChance;
	
	private ArrayList<Projectile> fireList;
	
	
	private boolean moveClick;
	
	private Ship ship;
	
	// Items
	private ArrayList<Item> items = new ArrayList<Item>();
	private MiningTool equipedMiningTool;
	private LaserBlaster equipedLaserBlaster;


	public Player(String name) {
		this.name = name;
		
		laserDamage = 50;
		criticalChance = 5;
		
		fireList = new ArrayList<Projectile>();
		
		
		
		
		
		
		ship = new Ship(ShipClasses.akira);
		
		
		/*
		 * MINING TOOL TEST BEGINNING
		 */
		
		String miningName = "Proton Chisel";
		int miningWeight = 1;
		double miningPower = 1;
		double miningRange = 50;
		int miningCost = 100;
		Color miningBeamColor = Color.AQUAMARINE;
		
		MiningTool protonChisel = new MiningTool(miningName, miningWeight, miningPower,
				miningRange, miningBeamColor);
		

		/*
		 * MINING TOOL TEST END
		 */
		
		/*
		 * LASER BLASTER TEST BEGINNING
		 */
		
		String blasterName = "Class 1 Defense Pulsor";
		int blasterWeight = 1;
		double blasterDamage = 25;
		double blasterCritical = 10;
		Color blasterColor = Color.LAWNGREEN;
		double blasterRange = 75;
		int blasterCost = 100;
		double blasterHeatRate = 5;
		double blasterCoolRate = 3;
		
		LaserBlaster defensePulsor = new LaserBlaster(blasterName, blasterWeight, blasterDamage,
				blasterCritical, blasterColor, blasterRange);
		
		
		/*
		 * LASER BLASTER TEST ENDING
		 */
		try {
		ship.equipItem(protonChisel);
		} catch (NotEnoughSpace e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Changes the players destination location variable, if the player is already moving somewhere movement stops, and calls move().
	 * Calling move() returns the calculated time it will take for the player to move between its current location and the destination
	 * 
	 * @param destination
	 * @return time, in milliseconds, to destination
	 */
	public double setDestination(Location destination) {

		this.destination = destination;

		if(movement.getStatus() == Animation.Status.RUNNING) {
			movement.stop();
		}

		return this.move();


	}

	public Node getSprite() {
		sprite = new Polygon();

		sprite.getPoints().addAll(new Double[] {
				0.0, 0.0,
				-3.0, 6.0,
				3.0, 6.0
		});

		sprite.setFill(Color.YELLOW);
		//sprite.setTranslateZ(1000);
		sprite.toFront();

		return sprite;
	}

	@Override
	public String toString() {
		return String.format("%s\nx: %.1f y: %.1f", name, location.getX(), location.getY());
	}

	@Override
	public Node getMinimapIcon() {
		
		Polygon minimapIcon = new Polygon();

		minimapIcon.getPoints().addAll(new Double[] {
				0.0, 0.0,
				-3.0, 6.0,
				3.0, 6.0
		});

		minimapIcon.setFill(Color.YELLOW);

		return minimapIcon;
	}

	public TranslateTransition getMovement() {
		return movement;
	}

	public void setMovement(TranslateTransition movement) {
		this.movement = movement;
	}
	
	public void setCoord(double x, double y) {
		location.setX(x);
		location.setY(y);
	}

	/**
	 * @return the fireList
	 */
	public ArrayList<Projectile> getFireList() {
		return fireList;
	}

	/**
	 * @return the equipedMiningTool
	 */
	public MiningTool getEquipedMiningTool() {
		return equipedMiningTool;
	}

	/**
	 * @param equipedMiningTool the equipedMiningTool to set
	 */
	public void setEquipedMiningTool(MiningTool equipedMiningTool) {
		this.equipedMiningTool = equipedMiningTool;
	}

	public LaserBlaster getEquipedLaserBlaster() {
		return equipedLaserBlaster;
	}

	public void setEquipedLaserBlaster(LaserBlaster equipedLaserBlaster) {
		this.equipedLaserBlaster = equipedLaserBlaster;
	}

	/**
	 * @param fireList the fireList to set
	 */
	public void setFireList(ArrayList<Projectile> fireList) {
		this.fireList = fireList;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the moveClick
	 */
	public boolean isMoveClick() {
		return moveClick;
	}

	/**
	 * @param moveClick the moveClick to set
	 */
	public void setMoveClick(boolean moveClick) {
		this.moveClick = moveClick;
	}

	/**
	 * Calculates the distance between the location variable and the destination variable, takes that value and the player speed
	 * to calculate the time it would take to move that distance, adjusts TranslateTransistion object 'movement' accordingly and
	 * runs the animation between location and destination for timeToDestination long on the player node.
	 * <br>
	 * Immediately after starting animation a task thread is run to be constantly updating the player location.
	 * 
	 * @return time, in milliseconds, to destination
	 */
	public double move() {
		Task<Void> updateLocation = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				while(movement.getStatus() == Animation.Status.RUNNING) {
					location.setX(sprite.getTranslateX());
					location.setY(sprite.getTranslateY());
				}
				return null;
			}

		};
		
		// Calculate the distance between location and destination
		double distanceToDestination = Calc.instance.getDistance(location.getX(), location.getY(),
				destination.getX(), destination.getY());

		// Use distance and speed to determine time to destination
		double timeToDestination = distanceToDestination/speed*1000;

		// Use time to destination to adjust TranslateTransition object 'movement'
		movement = new TranslateTransition();
		movement.setDuration(Duration.millis(timeToDestination));
		movement.setNode(sprite);
		movement.setToX(destination.getX());
		movement.setToY(destination.getY());
		
		// Smooth motion
		movement.setInterpolator(Interpolator.LINEAR);

		movement.play();

		new Thread(updateLocation).start();
		
		return timeToDestination;
		
		

	}
	
	public void fireLaser(Game game, double x, double y) {
		LaserBurst laserBurst = new LaserBurst(this, new Coord(x, y), game);
		
		game.getWorldGroup().getChildren().add(laserBurst.getSprite());
		fireList.add(laserBurst);
	}
	
	@Deprecated
	public void addItem(Item item, boolean setEquiped) {
		items.add(item);
		/*
		if(setEquiped) {
			switch(item.getItemType()) {
			case MININGTOOL:
				setEquipedMiningTool((MiningTool) item);
				break;
			case LASERBLASTER:
				setEquipedLaserBlaster((LaserBlaster) item);
				break;
			}
		}*/
	}


	@Override
	public boolean isDiscovered() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void laserHit(LaserBurst laserBurst) {
		// TODO Auto-generated method stub
		
	}
	
	
}
