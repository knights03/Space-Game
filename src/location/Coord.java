package location;

import javafx.scene.Node;

public class Coord extends Location {
	
	public Coord() {
		
	}

	public Coord(double x, double y) {
		setX(x);
		setY(y);
	}

	@Override
	public Node getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getMinimapIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this;
	}
}
