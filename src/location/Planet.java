package location;

import java.io.File;
import java.util.ArrayList;

import game.Game;
import game.World;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import particle.TorpedoBurst;
import settlement.Settlement;
import unit.Sprite;
import util.Calc;
import util.GlobalVars;
import util.RandomGenerator;

public class Planet extends Location {
	
	private static int planetNum = 0;

	private String name;
	private World world;
	private Game game;
	
	private ImageView sprite;
	private double size;
	private Color color;
	
	private ArrayList<Settlement> settlements = new ArrayList<Settlement>();
	
	public Planet(Game game, World world) {
		this.game = game;
		this.name = String.format("Planet%d", planetNum++);
		setLocationType(LocationType.PLANET);
		this.world = world;
		
		
		size = RandomGenerator.instance.getDouble(200, 100);

		StringBuilder planetImagePath = new StringBuilder(GlobalVars.PLANET_SPRITE_PATH);
		planetImagePath.append("planet");
		
		int numPlanetImages = 10;
		
		planetImagePath.append(RandomGenerator.instance.getInt(numPlanetImages));
		planetImagePath.append(".png");
		
		//Image image = new Image(planetImagePath.toString());
		Image image = new Image("file:images/planets/Ocean_planet1.png");
		
		sprite = new ImageView(image);
		
		
		setX(RandomGenerator.instance.getDouble(world.getW(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
		setY(RandomGenerator.instance.getDouble(world.getH(), 0, GlobalVars.MIN_LOCATION_SEPERATION));
		
		
		
		sprite.setTranslateX(getX());
		sprite.setTranslateY(getY());
		sprite.setTranslateZ(5000);
		sprite.toBack();
		
		
		sprite.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				double newX = event.getSceneX()+(game.getPlayer().getLocation().getX()) - GlobalVars.MAIN_WINDOW_WIDTH/2;
				double newY = event.getSceneY()+(game.getPlayer().getLocation().getY()) - GlobalVars.MAIN_WINDOW_HEIGHT/2;
				
				/*if(event.isSecondaryButtonDown() == false) {
					if(event.isControlDown() == false && event.isShiftDown() == false){
						game.playerMove(event.getSceneX(), event.getSceneY());
					} else if(event.isControlDown() == true) {
						//game.getWorldGroup().getChildren().add(new LaserBurst(game.getPlayer(),
						//		new Coord(newX, newY)).getSprite());

						game.getPlayer().fireLaser(game, newX, newY);
					} else if(event.isShiftDown() == true) {
						game.getWorldGroup().getChildren().add(new TorpedoBurst(game.getPlayer(),
								new Coord(newX, newY)).getSprite());
					}
				} else */
					planetInfoWindow().show();
				
				
			}
			
		});
		
		sprite.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				game.getGameScreen().setCursor(Cursor.HAND);
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
	
	public Planet(String name, Game game, World world) {
		this(game, world);
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
		sprite.setScaleX(size);
		sprite.setScaleY(size);
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void addSettlement(Settlement newSettlement) {
		settlements.add(newSettlement);
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
	
	
	public Stage planetInfoWindow() {
		Stage window = new Stage();
		window.setTitle(name);
		window.setHeight(GlobalVars.UNIT_INFO_WINDOW_HEIGHT);
		window.setWidth(GlobalVars.UNIT_INFO_WINDOW_WIDTH);
		
		VBox settlementsBox = new VBox();
		
		Scene planetInfoScene = new Scene(settlementsBox);
		
		Label settlementsLabel = new Label("Settlements");
		settlementsBox.getChildren().add(settlementsLabel);
		
		Button[] settlementsButtons = new Button[settlements.size()];
		
		for(int i = 0; i < settlements.size(); i++) {
			
			Settlement settlementI = settlements.get(i);
			
			settlementsButtons[i] = new Button(settlementI.getName());
			settlementsButtons[i].setOnAction(event->
				window.setScene(settlementI.infoWindow(window, planetInfoScene)));
			
			settlementsBox.getChildren().add(settlementsButtons[i]);
		}
		
		window.setScene(planetInfoScene);
		
		return window;
	}
	
}
