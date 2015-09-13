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
	
	/*
	 * At each of the end points the ship should, if its a
	 *  a) Planet: attempt to dock at settlement, if there one, and makes sure cargo and
	 *     money at are average midrange levels, increase money at Settlement; if no settlement
	 *     treat like a 
	 *  b) Coord: move a random distance in a random direction, pause
	 *     for a random amount of time (to allow trades with player), return to Trade
	 *     route
	 *  c) AsteroidCluster: same as Coord, except when the NPC leaves random point load up
	 *     asteroid cargo (iron, tritonite, etc each with lower chance)
	 *     
	 *     Start by creating interface TradePoint, implemented by Location and AsteroidCluster
	 *     AsteroidCluster needs a single x,y that it can be identified by (where the Trade
	 *     ships head for, where the icon appears on minimap)
	 *     
	 *     Change pointA and pointB in TradeRoute, fields as well as constructor parameter, to
	 *     type TradePoint
	 *     
	 *     TradePoint needs abstract method endpointBehaviour(NPC trader) which tells the ship
	 *     what to do when their TranslateTransition movement finishes (instead of setting
	 *     destination to pointB, what it does now)
	 */
	
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
