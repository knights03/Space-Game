package game;

import util.RandomGenerator;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BackgroundStar {
	
	private double x, y;
	private Circle sprite;
	
	public BackgroundStar(Game game) {
		x = RandomGenerator.instance.getDouble(game.getWorld().getW());
		y = RandomGenerator.instance.getDouble(game.getWorld().getH());
		
		sprite = new Circle(RandomGenerator.instance.getDouble(4), Color.WHITE);
		sprite.setTranslateX(x);
		sprite.setTranslateY(y);
		sprite.setTranslateZ(1000);
		sprite.toBack();
	}

	public Node getSprite() {
		return sprite;
	}
}
