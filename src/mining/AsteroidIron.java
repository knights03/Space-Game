package mining;

import cargo.CargoType;
import cargo.NotEnoughSpace;
import game.Game;
import game.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import location.Coord;
import location.Location;
import particle.MiningBeam;
import unit.Sprite;
import util.Calc;
import util.RandomGenerator;

public class AsteroidIron extends Asteroid implements Sprite {

	
	private double pointDistance(int yield) {
		return RandomGenerator.instance.getDouble(yield, yield / 2);
	}
	
	/**
	 * Generates a new Iron Asteroid. After setting Asteroid fields (location, yield, integrity) the Sprite for the Asteroid is
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
	 * @param yield How much iron it will produce when mined
	 * @param game The game object the asteroid is in
	 * @param homeCluster Which Asteroid Cluster it is located in
	 */
	public AsteroidIron(Location location, int yield, Game game,
			AsteroidCluster homeCluster) {
		
		super(game, homeCluster);
		
		setLocation(location);
		
		setYield(yield);
		setIntegrity(yield);
		setSprite(new Polygon());
		
		
		/*
		 *  Generate the random looking shape of the asteroid, starting with the source point
		 */
		Location source = new Coord(getSprite().getTranslateX(), getSprite().getTranslateY());
		
		// Set a random number of vertices
		int pointsCount = RandomGenerator.instance.getInt(8, 3);
		
		// Create an array with 2 indices for each point for x and y of that point [x1, y1, x2, y2, x3, y3 ...... ]
		Double[] points = new Double[pointsCount * 2];
		
		int pointAngle = 0;
		Location nextPoint;
		
		// For every two indices in the array use AngleDestination to determine the point of the next vertex
		for(int i = 0; i < points.length; i += 2) {
			nextPoint = Calc.instance.angleDestination(source, pointAngle, pointDistance(getYield()));
			
			points[i] = nextPoint.getX();
			points[i+1] = nextPoint.getY();
			pointAngle += ((2*Math.PI)/pointsCount);
		}
		
		((Polygon) getSprite()).getPoints().addAll(points);
		getSprite().setFill(Color.gray(RandomGenerator.instance.getDouble(1)));
		
		getSprite().setTranslateX(getLocation().getX());
		getSprite().setTranslateY(getLocation().getY());
		
		
		
		
		getSprite().setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent c) {
				// TODO Auto-generated method stub
								
				
				Player player = getGame().getPlayer();

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
								try {
									mine(player);
								} catch (NotEnoughSpace e) {
									// TODO Auto-generated catch block
									System.out.println("Not enough space");
								}
							}

						});

					} else {
						try {
							mine(player);
						} catch (NotEnoughSpace e) {
							// TODO Auto-generated catch block
							System.out.println("Not enough space");
						}
					}

				} else {
					game.getTextNotifier().addText("No Mining Tool equipped!", Color.RED);
				}
			}

		});
		
	}
	
	/**
	 * 
	 * 
	 * @param player
	 * @throws NotEnoughSpace
	 */
	private void mine(Player player) throws NotEnoughSpace {
		double miningPower = player.getShip().equippedMiningTool().getPower();
		
		//double miningPower = 12;
		
		decreaseIntegrity(miningPower);
		

		double decreaseSize = ((getIntegrity()/getYield())/2) + 0.5;
		
		getSprite().setScaleX(decreaseSize);
		getSprite().setScaleY(decreaseSize);
		
		new MiningBeam(getGame(), this.getLocation());

		
		System.out.println("Integrity: " + getIntegrity());
		
		if(getIntegrity() <= 0) {
			
			getGame().removeNode(getSprite());
			getGame().getPlayer().getShip().loadCargo(CargoType.IRON, getYield());
			getGame().getTextNotifier().addText(String.format("%dt of Iron received", getYield()));
		}
	}
	
	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return new Circle(1, Color.WHITE);
	}

	
	
}
