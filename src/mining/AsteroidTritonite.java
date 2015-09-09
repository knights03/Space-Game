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
import util.GlobalVars;
import util.RandomGenerator;

public class AsteroidTritonite extends Asteroid implements Sprite {

	public AsteroidTritonite(Location location, Game game, AsteroidCluster homeCluster) {
		
		super(game, homeCluster);
		
		setLocation(location);
		setIntegrity(getYield());
		
		generateSprite();
		
		getSprite().setFill(GlobalVars.TRITONITE_ASTEROID_COLOR);

		mineHandler(CargoType.TRITONITE);
		
		
	}
	
	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
