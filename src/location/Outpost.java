package location;

import unit.Sprite;
import util.GlobalVars;
import util.RandomGenerator;
import game.World;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Outpost extends Location {
	
	
	private static int outpostNum = 0;

	private String name;
	private World world;
	
	public Outpost(World world) {
		this.name = String.format("Outpost%d", outpostNum++);
		setLocationType(LocationType.OUTPOST);
		this.world = world;

		setX(RandomGenerator.instance.getDouble(world.getW(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
		setY(RandomGenerator.instance.getDouble(world.getH(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
	}

	
	@Override
	public String toString() {
		return String.format("Outpost\n%s\nx: %.1f y:%.1f", name, getX(), getY());
	}

	@Override
	public Node getSprite() {
		return new Circle(3, Color.RED);
	}


	@Override
	public Node getMinimapIcon() {
		Circle miniMapIcon = new Circle(3, Color.RED);
		
		miniMapIcon.setStroke(Color.BLACK);
		
		return miniMapIcon;
	}


	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this;
	}

}
