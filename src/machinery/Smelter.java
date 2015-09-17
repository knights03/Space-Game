package machinery;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

import cargo.CargoType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import settlement.Warehouse;

public class Smelter extends Machinery {
	
	private double smeltRate;
	private double efficiency;
	
	private Queue<MachineCargo> hopper = new LinkedList<MachineCargo>();
	private Hashtable<CargoType, Double> output;
	
	private Runnable process;

	public Smelter(String name, double smeltRate, double efficiency) {
		super(name, MachineryType.SMELTER);
		this.smeltRate = smeltRate;
		this.efficiency = efficiency;
		
		
		output = new Hashtable<CargoType, Double>();
		
		for(CargoType type : CargoType.values()) {
			output.put(type, 0.0);
		}
		
		//output = getFactory().getSettlement().getPlanet().getGame().emptyCargoTable();
		
		
		process = new Runnable() {

			@Override
			public void run() {
				System.out.println("Smelting started");
				while(!hopper.isEmpty()) {
					
					MachineCargo cargoToProcess = hopper.poll();
					
					while(!cargoToProcess.depleted()) {
						
						double outputAmount = output.get(cargoToProcess.getType());
						
						cargoToProcess.reduceAmount(smeltRate);
						output.put(cargoToProcess.getType(), outputAmount += smeltRate * efficiency);
						

						//getFactory().getSettlement().getPlanet().getGame().getTextNotifier().addText("Smelting...");
						System.out.println("Smelting...");
						
						

					}
					
				}
				
				getFactory().getSettlement().getPlanet().getGame().getTextNotifier().addText("Smelting complete", Color.LIMEGREEN);
				System.out.println("Smelting done!");
			}
			
		};
		
	}
	
	public void addToHopper(CargoType type, double amount) {
		hopper.add(new MachineCargo(type, amount));
	}
	
	public void operate() {



		Platform.runLater(process);
		
		
	}
	
	

}
