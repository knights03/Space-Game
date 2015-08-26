package mining;

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
	
	public AsteroidIron(Location location, int yield, Game game,
			AsteroidCluster homeCluster) {
		
		super(game, homeCluster);
		
		setLocation(location);
		
		setYield(yield);
		setIntegrity(yield);
		setSprite(new Polygon());
		
		Location source = new Coord(getSprite().getTranslateX(), getSprite().getTranslateY());
		
		int pointsCount = RandomGenerator.instance.getInt(8, 3);
		
		Double[] points = new Double[pointsCount * 2];
		
		int pointAngle = 0;
		Location nextPoint;
		
		for(int i = 0; i < points.length; i += 2) {
			nextPoint = Calc.instance.angleDestination(source, pointAngle, pointDistance(getYield()));
			
			points[i] = nextPoint.getX();
			points[i+1] = nextPoint.getY();
			pointAngle += (360/pointsCount);
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
				MiningTool miningTool = player.getEquipedMiningTool();
				
				
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
							mine(player);
						}

					});

				} else {
					mine(player);
				}

			}

		});
		
	}
	
	
	private void mine(Player player) {
		double miningPower = player.getEquipedMiningTool().getPower();
		
		//double miningPower = 12;
		
		decreaseIntegrity(miningPower);
		

		double decreaseSize = ((getIntegrity()/getYield())/2) + 0.5;
		
		getSprite().setScaleX(decreaseSize);
		getSprite().setScaleY(decreaseSize);
		
		new MiningBeam(getGame(), this.getLocation());

		
		System.out.println("Integrity: " + getIntegrity());
		
		if(getIntegrity() <= 0) {
			
			getGame().removeNode(getSprite());
		}
	}
	
	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return new Circle(1, Color.WHITE);
	}

	
	
}
