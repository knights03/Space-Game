package game;


import particle.LaserBurst;
import particle.TorpedoBurst;
import ship.ShipClasses;
import unit.Battleship;
import unit.Combatant;
import unit.Unit;
import util.GlobalVars;
import util.RandomGenerator;
import weapon.LaserBlaster;
import cargo.NotEnoughSpace;
import faction.Faction;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import location.Coord;
import location.Location;
import location.Planet;
import mining.MiningTool;

public class FXLauncher extends Application {

	public static void main(String[] args) {
		launch(args);

	}
	
	private static int counterInt = 0;
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		
		
		// Generate game 
		Game game = new Game("Jason", "JasonTown");
		
		Faction gbc = new Faction("Galactic Banking Corporation",
				Color.BLUE, Color.ORANGE);
		
		Faction cor = new Faction("Osiris Cultists", Color.GOLD, Color.MAROON);
		Faction pw = new Faction("Praetorian Warband", Color.PURPLE, Color.WHITE);
		
		game.addFaction(gbc, cor, pw);
		
		primaryStage = game.generatePrimaryStage();
		game.generateStoryWorld();
		
		
		/* Set window properties
		primaryStage.setTitle(GameConstants.MAIN_WINDOW_TITLE);
		primaryStage.setHeight(GameConstants.MAIN_WINDOW_HEIGHT);
		primaryStage.setWidth(GameConstants.MAIN_WINDOW_WIDTH);*/
		
		
		// Create a test unit heading southeast past the player
		/*
		String blasterName = "Repeating Disruptor Cannon";
		double blasterDamage = 500;
		double blasterCritical = 10;
		Color blasterColor = Color.RED;
		double blasterRange = 250;
		int blasterCost = 100;
		double blasterHeatRate = 5;
		double blasterCoolRate = 3;
		
		String miningName = "Ion Drill";
		double miningPower = 18;
		double miningRange = 500;
		int miningCost = 100;
		Color miningBeamColor = Color.GREEN;
		
		MiningTool ionDrill = new MiningTool(miningName, miningPower,
				miningRange, miningCost, miningBeamColor);
		
		LaserBlaster repeatingCanon = new LaserBlaster(blasterName, blasterDamage,
				blasterCritical, blasterColor, blasterRange, blasterCost,
				blasterHeatRate, blasterCoolRate);
		*/
		Unit testUnit = new Battleship("Warden Zeeb", game.getPlayer(), cor);
		testUnit.setLocation(new Coord(7300, 7500));
		testUnit.setDefense(30);
		//testUnit.addItem(repeatingCanon);
		//testUnit.addItem(ionDrill);
		
		/*
		for(int i = 0; i < 2; i++) {
			Unit testUnit2 = new Battleship("Enemy Battleship", game.getPlayer());
			testUnit2.setLocation(new Coord(300, 7500));
			
			
			Node testUnitNode = testUnit2.getSprite();
			testUnitNode.setTranslateX(testUnit2.getLocation().getX());
			testUnitNode.setTranslateY(testUnit2.getLocation().getY());
			game.getWorldGroup().getChildren().add(testUnitNode);
			game.getCombatantList().add((Combatant) testUnit2);
		}
		*/
		// Add the test unit node to the scene group
		Node testUnitNode = testUnit.getSprite();
		testUnitNode.setTranslateX(testUnit.getLocation().getX());
		testUnitNode.setTranslateY(testUnit.getLocation().getY());
		game.getWorldGroup().getChildren().add(testUnitNode);
		game.getCombatantList().add((Combatant) testUnit);
		
		// Adjust group to player location
		//game.getWorldGroup().setTranslateX(-1*(game.getPlayer().getLocation().getX()) + GameConstants.MAIN_WINDOW_WIDTH/2);
		//game.getWorldGroup().setTranslateY(-1*(game.getPlayer().getLocation().getY()) + GameConstants.MAIN_WINDOW_HEIGHT/2);
		
		// Change course button
		Button showInventory = new Button("Show Inventory");
		
		showInventory.setOnAction(new EventHandler<ActionEvent>() {
			

			@Override
			public void handle(ActionEvent arg0) {
				game.inventoryWindow().show();
			}
		});

		// Show map button
		Button showMap = new Button("Show map");
	
		showMap.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				game.showMap(game.getWorld());
				
			}
			
		});
		
		// Set player course button
		Button playerChangeCourse = new Button("Set course");
		
		playerChangeCourse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				game.newCourseWindow().show();
			}
			
		});
		
		

		Button addText = new Button("Test Notifier");

		addText.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//textNotifier.addText(String.format("%d", RandomGenerator.instance.getInt(10000)), RandomGenerator.instance.getColor());
			}

		});
		
		Button equipDefenseBlaster = new Button("Equip Defense Blaster");

		equipDefenseBlaster.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					game.getPlayer().getShip().equipItem(GameItems.class1DefensePulsor);
				} catch (NotEnoughSpace e) {
					game.getTextNotifier().addText("Too many equipped items!", Color.RED);
				}
			}

		});
		
		Button regenWorld = new Button("Generate new World");

		regenWorld.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				game.generateStoryWorld();
			}

		});
		
		// Add buttons to left tool box
		game.getLeftToolBox().getChildren().add(showMap);
		game.getLeftToolBox().getChildren().add(showInventory);
		game.getLeftToolBox().getChildren().add(playerChangeCourse);
		game.getLeftToolBox().getChildren().add(addText);
		game.getLeftToolBox().getChildren().add(equipDefenseBlaster);
		game.getLeftToolBox().getChildren().add(regenWorld);
		//game.getLeftToolBox().getChildren().add(textNotifier.getNotifierText());

		primaryStage.show();
		//testUnit.run();
		System.out.println(testUnit.getLocation().getX());
		System.out.println(testUnit.getLocation().getY());
		
		
		
		
		game.getClickArea().setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub;
				
				double newX = event.getSceneX()+(game.getPlayer().getLocation().getX()) - GlobalVars.MAIN_WINDOW_WIDTH/2;
				double newY = event.getSceneY()+(game.getPlayer().getLocation().getY()) - GlobalVars.MAIN_WINDOW_HEIGHT/2;
				
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
				
				if(game.getMap().isShowing()) {
					game.getMap().hide();
				}
				
			}
			
		});
		

		
		// Task tests
		Task<Void> loopTest = new Task() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				for(int i = 0; i < 100; i++) {
					System.out.println(String.format("x: %.1f y: %.1f", game.getPlayer().getLocation().getX(),
							game.getPlayer().getLocation().getY()));
					Thread.sleep(250);
				}
				return null;
			}
			
		};

		
		//new Thread(loopTest).start();
		
		/*
		VBox groupOne = new VBox();
		Scene sceneOne = new Scene(groupOne);
		VBox groupTwo = new VBox();
		Scene sceneTwo = new Scene(groupTwo);
		
		//sceneOne
		Button sceneOneButton = new Button("Click");
		Label counter = new Label(String.format("%d", counterInt));
		
		groupOne.getChildren().add(sceneOneButton);
		groupOne.getChildren().add(counter);
		

		sceneOneButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(sceneTwo);
			}
		});
		
		//sceneTwo
		Label testLabel = new Label("Second page");
		Button goBack = new Button("Go back");
		
		goBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub
				primaryStage.setScene(sceneOne);
			}
			
		});
		
		groupTwo.getChildren().add(testLabel);
		groupTwo.getChildren().add(goBack);
		
		
		
		
		
		primaryStage.setScene(sceneOne);
		primaryStage.show();
		
		//Counter increase

		


		
		
		// TODO Auto-generated method stub
		
		 * primaryStage.setTitle(GameConstants.MAIN_WINDOW_TITLE);
		primaryStage.setHeight(GameConstants.MAIN_WINDOW_HEIGHT);
		primaryStage.setWidth(GameConstants.MAIN_WINDOW_WIDTH);		
		
		Group root = new Group();
		
		World mainWorld = new World("JasonTown", 12000,
				12000);
		
		mainWorld.addCluster(1000, 0, 10000, 5000, 15, 2, 20);
		mainWorld.addCluster(1000, 5000, 5000, 10000, 5, 1, 10);
		
		Location earth = new Planet("Earth", mainWorld);
		earth.setX(10000);
		earth.setY(10000);
		
		mainWorld.addLocation(earth);
		
		Player jason = new Player();
		
		jason.setLocation(new Coord(11000, 11000));
		
		double xMult = GameConstants.MAIN_WINDOW_WIDTH/mainWorld.getW();
		double yMult = GameConstants.MAIN_WINDOW_HEIGHT/mainWorld.getH();
		
		for(Location location : mainWorld.getLocations()) {
			System.out.println(location);
			
			if(location.isDiscovered()) {
				Node newLocation = location.getSprite();
			
				newLocation.setTranslateX(location.getX() * xMult);
				newLocation.setTranslateY(location.getY() * yMult);
			
				Tooltip t = new Tooltip(location.toString());
				Tooltip.install(newLocation, t);
			
				root.getChildren().add(newLocation);
			}
		}
		
		Node playerSprite = jason.getSprite();
		
		playerSprite.setTranslateX(jason.getLocation().getX() * xMult);
		playerSprite.setTranslateY(jason.getLocation().getY() * yMult);
		
		root.getChildren().add(playerSprite);
		primaryStage.setScene(new Scene(root, Color.BLACK));*/
		
	}
}
