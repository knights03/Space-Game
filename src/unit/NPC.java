package unit;

import java.util.ArrayList;

import faction.Faction;
import game.Game;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import location.Coord;
import location.Location;
import particle.LaserBurst;
import particle.Projectile;
import ship.Ship;
import ship.ShipClass;
import weapon.LaserBlaster;

public class NPC implements Sprite, Combatant {
	
	
	private String name;
	private Ship ship;
	
	private Faction faction;
	
	private Location location;
	private Location destination;
	private TranslateTransition movement = new TranslateTransition();
	
	private ArrayList<Projectile> fireList;
	
	public NPC(String name, ShipClass shipClass, Game game) {
		this.name = name;
		this.ship = new Ship(shipClass, game);
	}

	@Override
	public ArrayList<Projectile> getFireList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LaserBlaster getEquipedLaserBlaster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void laserHit(LaserBurst laserBurst) {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getSprite() {
		// TODO Auto-generated method stub
		return ship.getSprite();
	}

	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDiscovered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

}
