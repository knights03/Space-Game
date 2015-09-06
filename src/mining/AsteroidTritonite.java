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
		
		int r = 93;
		int g = 70;
		int b = 0xDB;
		
		int modifier = RandomGenerator.instance.getInt(35, -70);
		
		r += modifier;
		g += modifier;
		b += modifier;
		
		Color color = Color.rgb(r, g, b);
		
		color.deriveColor(0, 0, -90, 0);
		
		getSprite().setFill(color);

		mineHandler(CargoType.TRITONITE);
		
		
	}
	
	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
