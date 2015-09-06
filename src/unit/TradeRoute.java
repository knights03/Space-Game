package unit;

import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import location.Coord;
import location.Location;
import ship.ShipClasses;
import util.Calc;

public class TradeRoute {
	
	private Location pointA, pointB;
	private Game game;
	
	private NPC trader;
	
	public TradeRoute(Location pointA, Location pointB, double density, Game game) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.game = game;
		
		int shipNum = (int) Math.round((Calc.instance.getDistance(pointA, pointB)/200)*density);
		shipNum = 1;
		
		
		for(int i = 0; i < shipNum; i++) {
			trader = new NPC("Trader", ShipClasses.akira, game);
			trader.setLocation(pointA);

			game.addSprite(trader);
			
			trader.setDestination(pointB);
			
			trader.getMovement().setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					trader.setLocation(pointA);
					trader.setDestination(pointA);
					System.out.println("Arrived at destination");
					
//					if(trader.getDestination() == pointB)
//						trader.setDestination(pointA);
//					else
//						trader.setDestination(pointB);
				}
				
			});
			
//			
			System.out.println(shipNum);
		}
	}

}
