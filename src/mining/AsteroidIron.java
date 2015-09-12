package mining;

import java.util.Hashtable;

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

public class AsteroidIron extends Asteroid implements Sprite {

	public AsteroidIron(Location location, Game game, AsteroidCluster homeCluster) {
		
		super(game, homeCluster);
		
		setLocation(location);
		setIntegrity(getYield());
		
		generateSprite();
		
		getSprite().setFill(Color.gray(RandomGenerator.instance.getDouble(1)));

		mineHandler(CargoType.UIRON);
		
		
	}
	
	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<CargoType, Double> getCargo() {
		
		Hashtable<CargoType, Double> cargo = new Hashtable<CargoType, Double>();
		
		cargo.put(CargoType.UIRON, getYield());
		
		return cargo;
		
	}

}
