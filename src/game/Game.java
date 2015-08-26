package game;



import java.util.ArrayList;

import faction.Faction;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import location.Coord;
import location.Location;
import location.Planet;
import mining.Asteroid;
import mining.AsteroidCluster;
import unit.*;
import util.Calc;
import util.GlobalVars;
import util.RandomGenerator;
import util.RareItemGenerator;
import util.BubbleSort;

public class Game {
	
	private Player player;
	private World world;
	
	// FX Launcher stage/scene
	private Stage primaryStage;
	private Stage map = new Stage();
	private Scene gameScreen;
	
	// Groups
	private Group ui;
	private Group worldGroup;
	private VBox leftToolBox;
	
	private Rectangle clickArea = new Rectangle();
	
	private TranslateTransition screenMove;
	private PerspectiveCamera fov = new PerspectiveCamera();
	
	
	private ArrayList<Combatant> combatantList = new ArrayList<Combatant>();
	private ArrayList<Faction> factionList = new ArrayList<Faction>();
	
	/**
	 * Constructs the game object, creates a player object and world object to be used for this game
	 * @param playerName The name of the player to be created
	 * @param worldName The name of the world to be created
	 */
	public Game(String playerName, String worldName) {
		player = new Player(playerName);
		player.setGame(this);
		world = new World(worldName);
		
		screenMove = new TranslateTransition();

	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @param world the world to set
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	public Group getUi() {
		return ui;
	}

	public Group getWorldGroup() {
		return worldGroup;
	}

	public VBox getLeftToolBox() {
		return leftToolBox;
	}

	public void setUi(Group ui) {
		this.ui = ui;
	}

	public void setWorldGroup(Group worldGroup) {
		this.worldGroup = worldGroup;
	}

	public void setLeftToolBox(VBox leftToolBox) {
		this.leftToolBox = leftToolBox;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @return the map
	 */
	public Stage getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Stage map) {
		this.map = map;
	}

	public Scene getGameScreen() {
		return gameScreen;
	}

	/**
	 * @return the clickArea
	 */
	public Rectangle getClickArea() {
		return clickArea;
	}

	/**
	 * @param clickArea the clickArea to set
	 */
	public void setClickArea(Rectangle clickArea) {
		this.clickArea = clickArea;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setGameScreen(Scene gameScreen) {
		this.gameScreen = gameScreen;
	}

	/**
	 * @return the combatantList
	 */
	public ArrayList<Combatant> getCombatantList() {
		return combatantList;
	}

	/**
	 * @return the factionList
	 */
	public ArrayList<Faction> getFactionList() {
		return factionList;
	}

	/**
	 * @param factionList the factionList to set
	 */
	public void setFactionList(ArrayList<Faction> factionList) {
		this.factionList = factionList;
	}
	
	public void addFaction(Faction... factions) {
		for(Faction faction : factions) {
			factionList.add(faction);
		}
	}

	/**
	 * @param combatantList the combatantList to set
	 */
	public void setCombatantList(ArrayList<Combatant> combatantList) {
		this.combatantList = combatantList;
	}

	
	/**
	 * Generates the primary stage for the game, setting up the window stage and creates the node groups for managing game sprites. It
	 * also sets up fov, the camera uses to create a sense of 3D perspective (stars float by slower when moving than other units)
	 * 
	 * @return The primary stage window
	 * 
	 */
	public Stage generatePrimaryStage() {
		
		// Set up the primary stage
		primaryStage = new Stage();
		
		primaryStage.setTitle(GlobalVars.MAIN_WINDOW_TITLE);
		primaryStage.setHeight(GlobalVars.MAIN_WINDOW_HEIGHT);
		primaryStage.setWidth(GlobalVars.MAIN_WINDOW_WIDTH);
		primaryStage.setResizable(false);
		
		// Add node groups
		ui = new Group();
		worldGroup = new Group();
		leftToolBox = new VBox();
		
		ui.getChildren().add(worldGroup);
		ui.getChildren().add(leftToolBox);
		
		// Set the stages scene to the game screen
		gameScreen = new Scene(ui, Color.BLACK);
		primaryStage.setScene(gameScreen);
		
		// Set up camera
		fov.setNearClip(0.1);
		fov.setFarClip(2000.0);
		fov.setFieldOfView(35);
		gameScreen.setCamera(fov);
		
		return primaryStage;
	}

	
	/**
	 * Generates the story world by setting world h and w and adding random planet and asteroid clusters. Used for testing purposes only
	 * as the final product should be all pre-defined planets and clusters
	 */
	public void generateStoryWorld() {
		
		world.setH(12000);
		world.setW(12000);
		
		world.addCluster(5000, 1000, 11000, 5000, 5, 1, 10);
		world.addCluster(1000, 5000, 11000, 11000, 15, 2, 20);
		
		

		// Set up the click area, an invisible rectangle the size of the game world which detects if the player
		// clicks anything thats not a sprite or button
		clickArea.setX(0);
		clickArea.setY(0);
		clickArea.setWidth(world.getW());
		clickArea.setHeight(world.getH());
		clickArea.setFill(Color.TURQUOISE);
		clickArea.setOpacity(0);
		
		worldGroup.getChildren().add(clickArea);
		
		// Add background stars
		for(int i = 0; i < GlobalVars.STAR_AMOUNT; i++) {
			worldGroup.getChildren().add(new BackgroundStar(this).getSprite());
		}
		
		
		// Test planet
		Planet startPlanet = new Planet("Prison World 327", world);
		startPlanet.setX(7700);
		startPlanet.setY(7700);
		startPlanet.setSize(350);
		startPlanet.setColor(Color.DARKBLUE);
		
		world.addLocation(startPlanet);

		// Add all world locations to the game window
		for(Location nextPlanet : world.getLocations()) {
			
			Node nextPlanetSprite = nextPlanet.getSprite();
			
			worldGroup.getChildren().add(nextPlanetSprite);
		}

		// Create an asteroid cluster
		AsteroidCluster kuperBelt = new AsteroidCluster(3000, 5000, 7000, 7000, 25, this);
		System.out.println();

		// Add the player
		player.setLocation(new Coord(7550, 7570));
		player.setSpeed(300);

		Node playerNode = player.getSprite();
		worldGroup.getChildren().add(playerNode);
		playerNode.setTranslateX(player.getLocation().getX());
		playerNode.setTranslateY(player.getLocation().getY());

		// Move the world group according to the players location
		worldGroup.setTranslateX(-1*(player.getLocation().getX()) + GlobalVars.MAIN_WINDOW_WIDTH/2);
		worldGroup.setTranslateY(-1*(player.getLocation().getY()) + GlobalVars.MAIN_WINDOW_HEIGHT/2);

		
		
	}
	
	
	/**
	 * Generates the minimap window, showing a scaled down representation of all discovered objects in the game world, and returns the Stage to be opened
	 * with show()
	 * @param world Which world the map should be shown for
	 * @return the map window
	 */
	public void showMap(World world) {

		// Set up window stage
		
		map.setTitle(GlobalVars.MAP_WINDOW_TITLE);
		map.setHeight(GlobalVars.MAP_WINDOW_HEIGHT);
		map.setWidth(GlobalVars.MAP_WINDOW_WIDTH);
		
		Group root = new Group();
		
		// Set the scale of world size:window size to show a scaled down version of the game world in a small window
		double xMult = GlobalVars.MAP_WINDOW_WIDTH/world.getW();
		double yMult = GlobalVars.MAP_WINDOW_HEIGHT/world.getH();
		
		// For each location in the game world add the minimap icon to the window
		for(Location location : world.getLocations()) {
			System.out.println(location);
			
			if(location.isDiscovered()) {
				Node newLocation = location.getMinimapIcon();
			
				newLocation.setTranslateX(location.getLocation().getX() * xMult);
				newLocation.setTranslateY(location.getLocation().getY() * yMult);
			
				Tooltip t = new Tooltip(location.toString());
				Tooltip.install(newLocation, t);
			
				root.getChildren().add(newLocation);
			}
		}
		/*
		// Add the asteroid minimap icons for each of the asteroid clusters
		for(AsteroidCluster cluster : world.getAsteroidClusters()) {
			for(Asteroid asteroid : cluster.getAsteroidList()) {
				Node asteroidNode = asteroid.getMinimapIcon();
				
				asteroidNode.setTranslateX(asteroid.getLocation().getX() * xMult);
				asteroidNode.setTranslateY(asteroid.getLocation().getY() * yMult);
				
			}
		}*/
		
		// Add the player minimap icon
		Node playerSprite = player.getMinimapIcon();
		
		playerSprite.setTranslateX(player.getLocation().getX() * xMult);
		playerSprite.setTranslateY(player.getLocation().getY() * yMult);
		
		Tooltip playerTT = new Tooltip(player.toString());
		Tooltip.install(playerSprite, playerTT);
		
		root.getChildren().add(playerSprite);
		
		map.setScene(new Scene(root, GlobalVars.MAP_WINDOW_BG_COLOUR));
		
		map.show();
	}
	
	
	/**
	 * Generates the window used for setting a new ship course, consisting of a slider that the uses to pick a heading, a text box to manually enter a heading, a button that closes the window
	 * and set the course on the desired heading
	 * @return the new course window
	 */
	public Stage newCourseWindow() {
		
		// Set up window Stage
		Stage newCourse = new Stage();
		
		newCourse.setTitle(GlobalVars.NEW_COURSE_WINDOW_TITLE);
		newCourse.setHeight(GlobalVars.NEW_COURSE_WINDOW_HEIGHT);
		newCourse.setWidth(GlobalVars.NEW_COURSE_WINDOW_WIDTH);
		
		// Stage nodes
		VBox courseSetting = new VBox();
		
		Slider headingSlider = new Slider(0, 360, 0);
		
		courseSetting.getChildren().add(headingSlider);
		
		// Heading
		Label headingLabel = new Label("Heading");
		TextField headingBox = new TextField(String.valueOf(headingSlider.getValue()));
		HBox headingGroup = new HBox(headingLabel, headingBox);
		
		courseSetting.getChildren().add(headingGroup);
		
		
		headingSlider.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				headingBox.setText(String.format("%.2f", headingSlider.getValue()));
			}
			
		});
		
		Button go = new Button("Go");
		Label error = new Label();
		
		go.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				
				boolean validHeading = false;
				double heading = headingSlider.getValue();

				while(validHeading == false) {
					try {
						heading = Double.parseDouble(headingBox.getText());
					}
					catch (NumberFormatException e){
						error.setText("Must be a number");
						validHeading = false;
						break;
					}
					catch (NullPointerException e) {
						error.setText("Please enter a number");
						validHeading = false;
						break;
					}
					validHeading = true;
					
				}

				if(validHeading == true){
					playerMove(Calc.instance.angleDestination(player.getLocation(), Math.toRadians(heading), 10000));
					newCourse.hide();
				}
			}
		});
		
		courseSetting.getChildren().add(go);
		courseSetting.getChildren().add(error);
		
		newCourse.setScene(new Scene(courseSetting));
		
		return newCourse;
	}
	
	/**
	 * Runs when the player is ordered to move. When the player movement TranslateTransistion animation is run, a similar
	 * animation is run in the opposite direction for the same distance and same time on the worldGroup, the group containing
	 * all visible game nodes including the player.
	 * <br>
	 * With the entire universe moving backwards as the player is moving forward, it appears to the user the player is stationary
	 * in the middle of the screen (the 'camera' stays focused on the player).
	 * <br>
	 * This method signature is used for long distance travel, such as when the user enters a heading and the player keeps moving
	 * until a new heading is set or short-distance mouse travel is used. For its varient used for short-distance mouse travel,
	 * use {@link Game#playerMove(double,double)}.
	 * 
	 * @param destination Location of where the player is moving toward
	 * @see Game#playerMove(double,double)
	 */
	
	
	public void playerMove(Location destination) {
		
		double screenMoveX = player.getLocation().getX() - destination.getX();
		double screenMoveY = player.getLocation().getY() - destination.getY();
		
		
		double screenMoveTime = player.setDestination(destination);

		
		if(screenMove.getStatus() == Animation.Status.RUNNING) {
			screenMove.stop();
		}
		
		
		
		screenMove.setByX(screenMoveX);
		screenMove.setByY(screenMoveY);
		
		screenMove.setDuration(Duration.millis(screenMoveTime));
		screenMove.setNode(worldGroup);
		
		screenMove.setInterpolator(Interpolator.LINEAR);
		
		screenMove.play();
	}
	
	/**
	 * Same as {@link Game#playerMove(Location)} except instead of passing a Location parameter two doubles are passed, the x and
	 * y coordinates that the player clicked (used for short-distance, mouse click travel)
	 * 
	 * @param screenX The x coordinate of where the user clicked on screen
	 * @param screenY The y coordinate of where the user clicked on screen
	 * @see Game#playerMove(Location)
	 */
	public void playerMove(double screenX, double screenY) {

		double newX = screenX + (player.getLocation().getX()) - GlobalVars.MAIN_WINDOW_WIDTH/2;
		double newY = screenY + (player.getLocation().getY()) - GlobalVars.MAIN_WINDOW_HEIGHT/2;
		
		System.out.printf("x: %.1f y: %.1f\n", newX, newY);
		
		
		double screenMoveX = player.getLocation().getX() - newX;
		double screenMoveY = player.getLocation().getY() - newY;
		
		
		double screenMoveTime = player.setDestination(new Coord(newX, newY));
		
		System.out.printf("x: %.1f y: %.1f\n", player.getLocation().getX(), player.getLocation().getY());

		
		if(screenMove.getStatus() == Animation.Status.RUNNING) {
			screenMove.stop();
		}
		
		
		
		screenMove.setByX(screenMoveX);
		screenMove.setByY(screenMoveY);
		
		screenMove.setDuration(Duration.millis(screenMoveTime));
		screenMove.setNode(worldGroup);
		
		screenMove.setInterpolator(Interpolator.LINEAR);
		
		screenMove.play();

	}
	
	/**
	 * Generates a window with the passed prompt String asking the user to select a number between the passed min and max values.
	 * @param prompt What the window asks
	 * @param max Max slider value
	 * @param min Min slider value
	 * @return the user-selected number
	 */
	public Stage howMuch(String prompt, int max, int min) {
		Stage howMuchWindow = new Stage();
		
		Label promptLabel = new Label(prompt);
		Slider amountSlider = new Slider(min, max, max);
		
		VBox root = new VBox(promptLabel, amountSlider);
		
		howMuchWindow.setScene(new Scene(root));
		
		return howMuchWindow;
	}
	
	/**
	 * Generates a window with the passed prompt String asking the user to select a number between zero and the passed max value.
	 * @param prompt What the window asks
	 * @param max Max slider value
	 * @return the user-selected number
	 */
	public Stage howMuch(String prompt, int max) {
		return howMuch(prompt, max, 0);
	}
	
	/**
	 * Generates a window asking the user to select "How much?" between zero and the passed max value.
	 * @param max Max slider value
	 * @return the user-selected number
	 */
	public Stage howMuch(int max) {
		return howMuch("How much?", max, 0);
	}
	
	/**
	 * Generates the window that pops up when the user clicks on and npc unit, showing unit details and the different options
	 * available to the user (trade, etc)
	 * @param unit The unit the user is interacting with
	 * @return the unit screen stage
	 */
	public Stage unitScreen(Unit unit) {
		// Setup the stage window
		Stage unitScreen = new Stage();
		
		unitScreen.setTitle(GlobalVars.UNIT_INFO_WINDOW_TITLE);
		unitScreen.setHeight(GlobalVars.UNIT_INFO_WINDOW_HEIGHT);
		unitScreen.setWidth(GlobalVars.UNIT_INFO_WINDOW_WIDTH);
		
		
		// Unit info box
		VBox unitInfoRoot = new VBox();
		Scene unitInfoScreen = new Scene(unitInfoRoot);
		
		
		Label info = new Label();
		info.setText(unit.toString());

		Button gotoHailButton = new Button("Talk");
		Button gotoTradeButton = new Button("Trade");
		Button acceptSurrender = new Button("Accept surrender");
		if(unit.isSurrendering() == false) {
			acceptSurrender.setVisible(false);
		}
		
		
		unitInfoRoot.getChildren().add(info);
		unitInfoRoot.getChildren().add(gotoHailButton);
		unitInfoRoot.getChildren().add(gotoTradeButton);
		unitInfoRoot.getChildren().add(acceptSurrender);
		
		unitScreen.setScene(unitInfoScreen);
		
		// Trade screen
		
		Trade npcTrade = new Trade(player, unit);
		
		Scene tradeScreen = npcTrade.showTradeScreen(unit);
		
		gotoTradeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				unitScreen.setScene(tradeScreen);
			}
			
		});
		
		return unitScreen;
	}
	
	/**
	 * Removes a specific node from the world group
	 * @param node Which node to remove
	 */
	public void removeNode(Node node) {
		worldGroup.getChildren().remove(node);
	}
	
	
	/*
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 0; i < 100; i++) {
			System.out.println("adsf");
			Thread.sleep(1000);
		}
		
		int shipAmount = 40;
		
		Unit[] testBattleships = new Battleship[shipAmount];

		for(int i = 0; i < shipAmount; i++) {
			
			testBattleships[i] = new Battleship();
			
			System.out.println(testBattleships[i]);

			testBattleships[i].consoleDisplayCargo();
			testBattleships[i].consoleDisplayResources();

		}
		
		System.out.println(RareItemGenerator.instance.newItemName());
		
		
		int testAmount = 300;
		double[] testArray = new double[testAmount];
		double total = 0;
		
		for(int i = 0; i < testAmount; i++) {
			testArray[i] = RandomGenerator.instance.lowRandomInt(3, 0);
			total += testArray[i];
		}
		
		testArray = BubbleSort.instance.sort(testArray);
		
		System.out.println("Lowest value: " + testArray[0]);
		System.out.println("Highest value: " + testArray[testAmount-1]);
		System.out.println("Average: " + total/testAmount);
		
		
		Unit newEnemy;
		
		newEnemy = new Battleship("A");
		Thread testA = new Thread(newEnemy, newEnemy.getName());
		testA.start();
		
		
		
		newEnemy = new Battleship("B");
		Thread testB = new Thread(newEnemy, newEnemy.getName());
		testB.start();
		
		System.out.println("B+C started");
		
		newEnemy = new Battleship("C");
		Thread testC = new Thread(newEnemy, newEnemy.getName());
		testC.start();
		
		
		System.out.println("ENd");
		//for(int i = 0; i < testAmount; i++)
		//	System.out.println(testArray[i]);
		
		Player jason = new Player();
		
		jason.setLocation(new Coord(140, 140));
		
		UnitFactory enemies = new UnitFactory(jason);
		
		for(int i = 0; i < 8; i++)
			enemies.newEnemy();
		
		System.out.println("Enemies");
		
		enemies.newCustomUnit("Xplodador", UnitType.BATTLESHIP);
		enemies.setCustomADSH(20000, 15000, 200, 1000);
		enemies.setCustomCargo(12, 13, 0, 15, 16);
		enemies.setCustomResource(17, 18, 19);
		enemies.addCustomUnit();
		
		enemies.newCustomUnit("Tornado", UnitType.CRUISER);
		enemies.setCustomADSH(100, 200, 300, 400);
		enemies.setCustomCargo(20, 21, 22, 0, 0);
		enemies.setCustomResource(23, 34, 45);
		enemies.addCustomUnit();
		
		for(Unit i : enemies.getUnitList()) {
			System.out.println(i);
			i.consoleDisplayCargo();
			i.consoleDisplayResources();
			System.out.println();
		}
		
		
		
		
		System.out.println("End"); 
		
		World jasonTown = new World("JasonTown", 1000, 1000, 5, 6, 2);
		
		System.out.println(jasonTown);
	}*/
}
