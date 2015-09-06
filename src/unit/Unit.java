package unit;

import faction.Faction;
import game.Game;
import game.Item;
import game.Player;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import location.Coord;
import location.Location;
import particle.LaserBurst;
import particle.Projectile;
import particle.TorpedoBurst;
import resource.Resource;
import util.GlobalVars;
import weapon.LaserBlaster;

public abstract class Unit implements Sprite {
	
	private static int unitID = 0;
	
	private String name;
	private double attack;
	private double defense;
	private double speed;
	private double direction;
	private double health;
	
	
	private Faction unitFaction;
	
	private boolean alive;
	
	private UnitType unitType;
	
	private ArrayList<Resource> resources;
	private int cargoCapacity;
	
	private Location location;
	private Location destination;
	
	private boolean visible;
	
	private Node sprite;
	private TranslateTransition movement = new TranslateTransition();
	private Point2D screenLocation;
	
	private ArrayList<Projectile> fireList = new ArrayList<Projectile>();
	
	private Player player;
	private Game game;
	
	private LaserBlaster equipedLaserBlaster;
	
	private EventHandler<MouseEvent> unitClick;
	
	private boolean surrender;
	private double surrenderThreshold;
	
	private double tradeLeniency;
	
	
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public Unit(Player player, Faction faction, Game game){
		name = String.format("Unit%d", unitID);
		unitID++;
		this.player = player;
		this.unitFaction = faction;
		this.game = game;
		
		unitClick = new EventHandler<MouseEvent>() {


			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub;

				double newX = event.getSceneX()+(player.getLocation().getX()) - GlobalVars.MAIN_WINDOW_WIDTH/2;
				double newY = event.getSceneY()+(player.getLocation().getY()) - GlobalVars.MAIN_WINDOW_HEIGHT/2;

				if(event.isControlDown() == false && event.isShiftDown() == false){
					//player.getGame().unitScreen(Unit.this).show();
					die();
				} else if(event.isControlDown() == true) {
					//game.getWorldGroup().getChildren().add(new LaserBurst(game.getPlayer(),
					//		new Coord(newX, newY)).getSprite());

					player.fireLaser(player.getGame(), newX, newY);
				} else if(event.isShiftDown() == true) {
					player.getGame().getWorldGroup().getChildren().add(new TorpedoBurst(player.getGame().getPlayer(),
							new Coord(newX, newY)).getSprite());
				}
			}

		};
		
		
		
		setTradeLeniency(10);
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the attack
	 */
	public double getAttack() {
		return attack;
	}
	/**
	 * @return the defense
	 */
	public double getDefense() {
		return defense;
	}
	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @return the direction
	 */
	public double getDirection() {
		return direction;
	}
	/**
	 * @return the unitType
	 */
	public UnitType getUnitType() {
		return unitType;
	}
	/**
	 * @return the resources
	 */
	public ArrayList<Resource> getResources() {
		return resources;
	}

	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * @return the surrender
	 */
	public boolean isSurrendering() {
		return surrender;
	}

	/**
	 * @return the surrenderThreshold
	 */
	public double getSurrenderThreshold() {
		return surrenderThreshold;
	}

	/**
	 * @param surrender the surrender to set
	 */
	public void setSurrender(boolean surrender) {
		this.surrender = surrender;
	}

	/**
	 * @param surrenderThreshold the surrenderThreshold to set
	 */
	public void setSurrenderThreshold(double surrenderThreshold) {
		this.surrenderThreshold = surrenderThreshold;
	}

	/**
	 * @return the unitFaction
	 */
	public Faction getUnitFaction() {
		return unitFaction;
	}

	/**
	 * @param unitFaction the unitFaction to set
	 */
	public void setUnitFaction(Faction unitFaction) {
		this.unitFaction = unitFaction;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	
	
	public void reduceHealth(double amount) {
		this.health -= amount;
	}
	
	/**
	 * @return the cargoCapacity
	 */
	public int getCargoCapacity() {
		return cargoCapacity;
	}
	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	/**
	 * @return the destination
	 */
	public Location getDestination() {
		return destination;
	}
	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}
	/**
	 * @return the avatar
	 */
	public Node getSprite() {
		return sprite;
	}
	/**
	 * @return the movement
	 */
	public TranslateTransition getMovement() {
		return movement;
	}
	/**
	 * @return the screenLocation
	 */
	public Point2D getScreenLocation() {
		return screenLocation;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param attack the attack to set
	 */
	public void setAttack(double attack) {
		this.attack = attack;
	}
	/**
	 * @param defense the defense to set
	 */
	public void setDefense(double defense) {
		this.defense = defense;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(double direction) {
		this.direction = direction;
	}
	/**
	 * @return the unitClick
	 */
	public EventHandler<MouseEvent> getUnitClick() {
		return unitClick;
	}

	/**
	 * @param unitClick the unitClick to set
	 */
	public void setUnitClick(EventHandler<MouseEvent> unitClick) {
		this.unitClick = unitClick;
	}

	/**
	 * @param unitType the unitType to set
	 */
	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}
	/**
	 * @param resources the resources to set
	 */
	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}

	
	
	/**
	 * @param cargoCapacity the cargoCapacity to set
	 */
	public void setCargoCapacity(int cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(Location destination) {
		
		this.destination = destination;
		
		if(movement.getStatus() == Animation.Status.RUNNING) {
			movement.stop();
		}
		
		this.run();
		
		
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	 * @param avatar the avatar to set
	 */
	public void setSprite(Node sprite) {
		this.sprite = sprite;
		
		sprite.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				game.getGameScreen().setCursor(Cursor.CROSSHAIR);
			}
			
		});
		
		sprite.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				game.getGameScreen().setCursor(Cursor.DEFAULT);
			}
			
		});
	}
	/**
	 * @param movement the movement to set
	 */
	public void setMovement(TranslateTransition movement) {
		this.movement = movement;
	}
	/**
	 * @param screenLocation the screenLocation to set
	 */
	public void setScreenLocation(Point2D screenLocation) {
		this.screenLocation = screenLocation;
	}
	
	/**
	 * @return the fireList
	 */
	public ArrayList<Projectile> getFireList() {
		return fireList;
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
	 * @return the tradeLeniency
	 */
	public double getTradeLeniency() {
		return tradeLeniency;
	}

	/**
	 * @param tradeLeniency the tradeLeniency to set
	 */
	public void setTradeLeniency(double tradeLeniency) {
		this.tradeLeniency = tradeLeniency;
	}

	@Override
	public String toString() {
		return String.format("%s\n%s (Attack: %.1f Defense: %.1f Speed: %.1f Health: %.1f)", getUnitType(), getName(),
				getAttack(), getDefense(), getSpeed(), getHealth());
	}
	
	
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void updateScreenLocation() {
		
		
		
	}
	
	public void run() {
		
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
		
		double xDistanceToDestination = Math.abs(location.getX()-destination.getX());
		double yDistanceToDestination = Math.abs(location.getY()-destination.getY());
		
		double distanceToDestination = Math.sqrt(Math.pow(xDistanceToDestination, 2)
				+ Math.pow(yDistanceToDestination, 2));
		
		double timeToDestination = distanceToDestination/speed*1000;
		
		movement = new TranslateTransition(Duration.millis(timeToDestination), sprite);
		movement.setToX(destination.getX());
		movement.setToY(destination.getY());

		movement.play();
		
		new Thread(updateLocation).start();
		
	}
	
	public boolean isDiscovered() {
		return false;
	}
	
	public void laserHit(LaserBurst laserBurst) {
		double laserDamage = laserBurst.getSource().getEquipedLaserBlaster().getLaserDamage();
		//double laserDamage = 12;

		System.out.println("Laser Damage: " + laserDamage);
		
		double armorFactor = laserDamage/getDefense();
		
		if(armorFactor < 0.1) {
			armorFactor = 0.1;
		}
		
		if(armorFactor > 1) {
			armorFactor = 1;
		}
		
		System.out.println("ArmorFactor: " + armorFactor);
		
		double damageAmount = laserDamage * armorFactor;
		
		reduceHealth(damageAmount);
		System.out.println("Damage Amount: " + damageAmount);
		
		if(health <= 0) {
			alive = false;
			die();
		}
	}
	
	public void die() {
		//TO BE OVERWRITTEN
		System.out.println("DEAD");
		//player.getGame().getWorldGroup().getChildren().remove(getSprite());
		
		if(player.getGame().getWorldGroup().getChildren().contains(getSprite())) {
			System.out.println("Contains");
		} else {
			System.out.println("Does not contain");
		}
		
		
		
		setSprite(new Circle(10, Color.RED));
	}
	
	
	/*
	public void run() {
		while(isAlive()) {
			
			double xDistanceToDestination = Math.abs(location.getX()-destination.getX());
			double yDistanceToDestination = Math.abs(location.getY()-destination.getY());
			
			double distanceToDestination = Math.sqrt(Math.pow(xDistanceToDestination, 2)
					+ Math.pow(yDistanceToDestination, 2));
			
			double playerXcomp = player.getLocation().getX() + (GameConstants.GAME_SCREEN_WIDTH/2);
			double playerYcomp = player.getLocation().getY() + (GameConstants.GAME_SCREEN_HEIGHT/2);
			
			movement.setToX(destination.getX() - playerXcomp);
			movement.setToY(destination.getY() - playerYcomp);
			movement.setDuration(Duration.millis(2000));
		}
		
	}
	*/
}
