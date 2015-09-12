package mining;

import java.util.Hashtable;

import cargo.Cargo;
import cargo.CargoType;
import game.Game;
import game.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import location.Coord;
import location.Location;
import particle.MiningBeam;
import unit.Sprite;
import util.Calc;
import util.GlobalVars;
import util.RandomGenerator;

public abstract class Asteroid implements Sprite, Cargo {
	
	private double integrity;
	private double yield;
	private Game game;
	private AsteroidCluster homeCluster;
	private boolean discovered;
	
	private Location location;

	
	private Shape sprite = new Polygon();

	/**
	 * Generates a new Asteroid. After setting Asteroid fields (location, yield, integrity) the Sprite for the Asteroid is
	 * created.
	 * <ol>
	 * <li>First a random number between 3 and 8 is generated; this is the number of sides the Asteroid has</li>
	 * <li>An array of points is created with the size being (number of points)*2, these will store the location of each vertex</li>
	 * <li>A loop runs and for every other array index (the first of every set of vertex coordinates) angleDestination is used
	 * to determine where that vertex is; the source being the Asteroid Location, the angle being (2pi / number of sides) * vertex
	 * number, and the distance being a random number based on the Asteroid's Yield</li>
	 * <li>Using this array of points a polygon is generated and filled with a random shade of gray</li>
	 * </ol>
	 * An EventHandler is then assigned to the polygon's onMousePressed which first checks to see if a MiningTool is equipped by the
	 * player. If a MiningTool is equipped the distance between the player and the Asteroid is compared with the MiningTool's range;
	 * if the player is out of range the game.playerMove() method is called, the destination being a point in between the player
	 * and the Asteroid, distance between (distance between player and asteroid - Mining Tool range).<br>
	 * If the player is already in range (or is now in range after being moved) the mine() method is called.
	 * 
	 * @param location The Location of the Asteroid
	 * @param yield How much it will produce when mined
	 * @param game The game object the asteroid is in
	 * @param homeCluster Which Asteroid Cluster it is located in
	 */
	public Asteroid(Game game, AsteroidCluster homeCluster) {
		setGame(game);
		setHomeCluster(homeCluster);
		//setLocation(new Coord(sprite.getTranslateX(), sprite.getTranslateY()));
		yield = RandomGenerator.instance.getDouble(GlobalVars.ASTEROID_YIELD_MAX, GlobalVars.ASTEROID_YIELD_MIN);
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the sprite
	 */
	public Shape getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Shape sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the integrity
	 */
	public double getIntegrity() {
		return integrity;
	}

	/**
	 * @return the yield
	 */
	public double getYield() {
		return yield;
	}

	/**
	 * @param integrity the integrity to set
	 */
	public void setIntegrity(double integrity) {
		this.integrity = integrity;
	}

	
	public void decreaseIntegrity(double amount) {
		integrity -= amount;
	}
	
	/**
	 * @param yield the yield to set
	 */
	public void setYield(double yield) {
		this.yield = yield;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the homeCluster
	 */
	public AsteroidCluster getHomeCluster() {
		return homeCluster;
	}

	/**
	 * @param homeCluster the homeCluster to set
	 */
	public void setHomeCluster(AsteroidCluster homeCluster) {
		this.homeCluster = homeCluster;
	}
	
	public boolean isDiscovered() {
		return discovered;
	}
	
	
	
	
	
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	} 
	
	protected void mine(Player player, CargoType cargoType) {
		double miningPower = player.getShip().equippedMiningTool().getPower();
		
		//double miningPower = 12;
		
		decreaseIntegrity(miningPower);
		

		double decreaseSize = ((getIntegrity()/getYield())/2) + 0.5;
		
		sprite.setScaleX(decreaseSize);
		sprite.setScaleY(decreaseSize);
		
		new MiningBeam(getGame(), this.getLocation());

		
		System.out.println("Integrity: " + getIntegrity());
		
		if(getIntegrity() <= 0) {
			
			//getGame().removeNode(sprite);
			//getGame().getPlayer().getShip().loadCargo(cargoType, getYield());
			getGame().loadCargo(cargoType, getYield(), this, getGame().getPlayer().getShip());
		}
	}


	private double pointDistance(double yield) {
		return RandomGenerator.instance.getDouble(yield, yield / 2);
	}

	protected void generateSprite() {



		/*
		 *  Generate the random looking shape of the asteroid, starting with the source point
		 */
		Location source = new Coord(sprite.getTranslateX(), sprite.getTranslateY());

		// Set a random number of vertices
		int pointsCount = RandomGenerator.instance.getInt(8, 3);

		// Create an array with 2 indices for each point for x and y of that point [x1, y1, x2, y2, x3, y3 ...... ]
		Double[] points = new Double[pointsCount * 2];

		int pointAngle = 0;
		Location nextPoint;

		// For every two indices in the array use AngleDestination to determine the point of the next vertex
		for(int i = 0; i < points.length; i += 2) {
			nextPoint = Calc.instance.angleDestination(source, pointAngle, pointDistance(yield));

			points[i] = nextPoint.getX();
			points[i+1] = nextPoint.getY();
			pointAngle += ((2*Math.PI)/pointsCount);
		}

		((Polygon) sprite).getPoints().addAll(points);

		sprite.setTranslateX(getLocation().getX());
		sprite.setTranslateY(getLocation().getY());

	}

	protected void mineHandler(CargoType cargoType) {
		sprite.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent c) {
				// TODO Auto-generated method stub


				Player player = game.getPlayer();

				if(player.getShip().equippedMiningTool() != null) {
					MiningTool miningTool = player.getShip().equippedMiningTool();
					System.out.println("Clank clank clank");

					double distanceFromPlayer = Calc.instance.getDistance(
							player.getLocation(), getLocation());

					if(distanceFromPlayer > miningTool.getRange()) {

						double moveDistance = distanceFromPlayer - miningTool.getRange() + 1;
						Location withinRange = Calc.instance.vectorDestination(player.getLocation(),
								getLocation(), moveDistance);
						game.playerMove(withinRange);

						player.getMovement().setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								mine(player, cargoType);
							}

						});

					} else {
						mine(player, cargoType);
					}

				} else {
					game.getTextNotifier().addText("No Mining Tool equipped!", Color.RED);
				}
			}

		});
	}
	


	@Override
	public double getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unload(CargoType cargoType, double amount) {
		// TODO Auto-generated method stub
		getGame().removeNode(sprite);
	}

}
