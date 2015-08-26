package location;

import game.World;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import unit.Sprite;
import util.Calc;
import util.GlobalVars;
import util.RandomGenerator;

public class Planet extends Location {
	
	private static int planetNum = 0;

	private String name;
	private World world;
	
	private Shape sprite;
	private double size;
	private Color color;
	
	public Planet(World world) {
		this.name = String.format("Planet%d", planetNum++);
		setLocationType(LocationType.PLANET);
		this.world = world;
		
		
		size = RandomGenerator.instance.getDouble(200, 100);
		color = RandomGenerator.instance.getColor();
		sprite = new Circle(size, color);
		
		setX(RandomGenerator.instance.getDouble(world.getW(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
		setY(RandomGenerator.instance.getDouble(world.getH(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
		
		
		
		sprite.setTranslateX(getX());
		sprite.setTranslateY(getY());
		sprite.setTranslateZ(RandomGenerator.instance.getDouble(300, 700));
		sprite.toBack();
		
	}
	
	public Planet(String name, World world) {
		this(world);
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(double size) {
		this.size = size;
		((Circle) sprite).setRadius(size);
		//sprite = new Circle(size, color);
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
		sprite.setFill(color);
	}
	
	@Override
	public void setX(double x) {
		super.setX(x);
		sprite.setTranslateX(x);
	}
	
	@Override
	public void setY(double y) {
		super.setY(y);
		sprite.setTranslateY(y);
	}

	@Override
	public String toString() {
		return String.format("Planet\n%s\nx: %.1f y: %.1f", name, getX(), getY());
	}
	
	public Node getSprite() {
		
		return sprite;
	}

	@Override
	public Node getMinimapIcon() {
		
		Circle miniMapIcon = new Circle(3, Color.BLUE);
		
		miniMapIcon.setStroke(Color.BLACK);
		
		return miniMapIcon;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this;
	}
	
}
