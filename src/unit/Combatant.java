package unit;

import java.util.ArrayList;

import javafx.scene.Node;
import location.Location;
import particle.LaserBurst;
import particle.Projectile;
import weapon.LaserBlaster;

public interface Combatant {

	public Location getLocation();
	public ArrayList<Projectile> getFireList();
	public LaserBlaster getEquipedLaserBlaster();
	public Node getSprite();
	public void laserHit(LaserBurst laserBurst);
}
