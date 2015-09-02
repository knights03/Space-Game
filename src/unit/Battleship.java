package unit;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import location.Coord;
import particle.TorpedoBurst;
import game.Player;
import faction.Faction;
import util.GlobalVars;
import util.RandomGenerator;

public class Battleship extends Unit implements Sprite, Combatant {
	public static final double MAX_ATTACK = 2000;
	public static final double MAX_DEFENSE = 2000;
	public static final double MAX_SPEED = 2000;
	public static final double MAX_HEALTH = 2000;
	public static final int MAX_CARGO = 30;
	public static final int MAX_FUEL = 30;
	public static final int MAX_SHEKEL = 30;
	public static final int MAX_TORPEDO = 30;
	public static final int MAX_RARE_ITEMS = 3;
	
	public static final double MIN_ATTACK = 1000;
	public static final double MIN_DEFENSE = 1000;
	public static final double MIN_SPEED = 1000;
	public static final double MIN_HEALTH = 1000;
	public static final int MIN_CARGO = 3;
	public static final int MIN_FUEL = 5;
	public static final int MIN_SHEKEL = 5;
	public static final int MIN_TORPEDO = 5;

	
	public Battleship(Player player, Faction faction) {
		
		super(player, faction);
		
		setUnitType(UnitType.BATTLESHIP);
		
		setAttack(RandomGenerator.instance.getDouble(MAX_ATTACK, MIN_ATTACK));
		setDefense(RandomGenerator.instance.getDouble(MAX_DEFENSE, MIN_DEFENSE));
		setSpeed(RandomGenerator.instance.getDouble(MAX_SPEED, MIN_SPEED));
		setHealth(RandomGenerator.instance.getDouble(MAX_HEALTH, MIN_HEALTH));
		
		
		Shape sprite = new Circle(30);
		sprite.setFill(getUnitFaction().getPrimaryColor());
		sprite.setStroke(getUnitFaction().getSecondaryColor());
		sprite.setStrokeWidth(2);
		
		setSprite(sprite);
		/*
		sprite.setOnMouseClicked(new EventHandler<MouseEvent>() {


			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub;

				double newX = event.getSceneX()+(player.getLocation().getX()) - GameConstants.MAIN_WINDOW_WIDTH/2;
				double newY = event.getSceneY()+(player.getLocation().getY()) - GameConstants.MAIN_WINDOW_HEIGHT/2;

				if(event.isControlDown() == false && event.isShiftDown() == false){
					// OPEN UNIT CLICK MENU (TRADE ETC)
				} else if(event.isControlDown() == true) {
					//game.getWorldGroup().getChildren().add(new LaserBurst(game.getPlayer(),
					//		new Coord(newX, newY)).getSprite());

					player.fireLaser(player.getGame(), newX, newY);
				} else if(event.isShiftDown() == true) {
					player.getGame().getWorldGroup().getChildren().add(new TorpedoBurst(player.getGame().getPlayer(),
							new Coord(newX, newY)).getSprite());
				}
			}

		});*/
		
		sprite.setOnMouseClicked(getUnitClick());
	}
	
	public Battleship(String name, Player player, Faction faction) {
		this(player, faction);
		
		setName(name);
	}



	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
