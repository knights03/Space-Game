package settlement;

import java.util.ArrayList;

import faction.Faction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import location.Planet;

public class Settlement {
	
	private String name;
	private int population;
	private Planet planet;
	
	private Faction faction;
	
	private ArrayList<Building> buildings = new ArrayList<Building>();
	
	public Settlement(String name, Faction faction, Planet planet) {
		this.name = name;
		this.faction = faction;
		this.population = 0;
		this.planet = planet;
	}

	
	public String getName() {
		return name;
	}
	
	public Scene infoWindow(Stage stage, Scene previousScene) {
		VBox infoWindowBox = new VBox();
		Label settlementInfoTitle = new Label(name);
		Button goBack = new Button("Go back");
		
		goBack.setOnAction(event->stage.setScene(previousScene));
		
		infoWindowBox.getChildren().addAll(settlementInfoTitle, goBack);
		
		return new Scene(infoWindowBox);
	}
	
	public void addBuilding(Building building) {
		buildings.add(building);
	}
	
	public Planet getPlanet() {
		return planet;
	}
}
