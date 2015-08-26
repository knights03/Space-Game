package util;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import unit.*;

public class SpriteGenerator {

	public static SpriteGenerator instance = new SpriteGenerator();
	
	public Node enemySpaceship(Unit unit) {
		switch(unit.getUnitType()) {
		case BATTLESHIP:
			return new Circle(10, Color.RED);
		case FIGHTER:
			return new Circle(3, Color.RED);
		default:
			return new Circle(5, Color.GRAY);	
			
		}
	}
}
