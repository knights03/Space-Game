package mining;

import game.Game;
import game.Player;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import location.Coord;
import location.Location;
import unit.Sprite;

public abstract class Asteroid implements Sprite {
	
	private double integrity;
	private int yield;
	private Game game;
	private AsteroidCluster homeCluster;
	private boolean discovered;
	
	private Location location;

	
	private Shape sprite;

	
	public Asteroid(Game game, AsteroidCluster homeCluster) {
		setGame(game);
		setHomeCluster(homeCluster);
		//setLocation(new Coord(sprite.getTranslateX(), sprite.getTranslateY()));
	}
	
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the sprite
	 */
	public Shape getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Shape sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the integrity
	 */
	public double getIntegrity() {
		return integrity;
	}

	/**
	 * @return the yield
	 */
	public int getYield() {
		return yield;
	}

	/**
	 * @param integrity the integrity to set
	 */
	public void setIntegrity(double integrity) {
		this.integrity = integrity;
	}

	
	public void decreaseIntegrity(double amount) {
		integrity -= amount;
	}
	
	/**
	 * @param yield the yield to set
	 */
	public void setYield(int yield) {
		this.yield = yield;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the homeCluster
	 */
	public AsteroidCluster getHomeCluster() {
		return homeCluster;
	}

	/**
	 * @param homeCluster the homeCluster to set
	 */
	public void setHomeCluster(AsteroidCluster homeCluster) {
		this.homeCluster = homeCluster;
	}
	
	public boolean isDiscovered() {
		return discovered;
	}
	
	
	
	
	
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	} 

}
