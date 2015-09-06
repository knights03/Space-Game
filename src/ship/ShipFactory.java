package ship;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.GlobalVars;

public class ShipFactory {
	
	public static ShipFactory instance = new ShipFactory();
	
	public ImageView newShip(ShipClasses.Classes shipClass) {
		StringBuilder spritePath = new StringBuilder(GlobalVars.SHIP_SPRITE_PATH);
		
		switch(shipClass) {
		case AKIRA:
			spritePath.append("akira.png");
			break;
		case EXCELSIOR:
			spritePath.append("excelsior.png");
			break;
		}
		
		return new ImageView(new Image(spritePath.toString()));
	}

}
