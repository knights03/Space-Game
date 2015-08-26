package unit;

import javafx.scene.Node;
import javafx.scene.Scene;
import location.Location;

public interface Sprite {
	public Node getSprite();
	public Node getMinimapIcon();
	public boolean isDiscovered();
	public Location getLocation();
}
