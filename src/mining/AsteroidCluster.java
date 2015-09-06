package mining;

import java.util.ArrayList;

import game.Game;
import location.Coord;
import util.RandomGenerator;

public class AsteroidCluster {

	private double x1, y1, x2, y2;
	private double density;
	private Game game;
	
	private ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	
	public AsteroidCluster(double x1, double y1, double x2, double y2,
			double density, Game game) {
		
		this.game = game;
		
		int asteroidCount = (int)Math.round((x2-x1)*(y2-y1)*(0.00001*density));
		
		System.out.println("Density" + asteroidCount);
		
		for(int i = 0; i < asteroidCount; i++) {
			
			double asteroidPicker = RandomGenerator.instance.getDouble(100);
			
			Asteroid newAsteroid;
			
			if(asteroidPicker < 90) {	
			newAsteroid = new AsteroidIron(
					new Coord(	RandomGenerator.instance.getDouble(x2, x1),
								RandomGenerator.instance.getDouble(y2, y1)), game, this);
			} else {
			newAsteroid = new AsteroidTritonite(
						new Coord(	RandomGenerator.instance.getDouble(x2, x1),
									RandomGenerator.instance.getDouble(y2, y1)), game, this);
			}
			
			asteroidList.add(newAsteroid);
			game.getWorldGroup().getChildren().add(newAsteroid.getSprite());
		}
	}

	/**
	 * @return the asteroidList
	 */
	public ArrayList<Asteroid> getAsteroidList() {
		return asteroidList;
	}

	/**
	 * @param asteroidList the asteroidList to set
	 */
	public void setAsteroidList(ArrayList<Asteroid> asteroidList) {
		this.asteroidList = asteroidList;
	}
}
