package ship;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.GlobalVars;

public class ShipFactory {
	
	public static ShipFactory instance = new ShipFactory();
	
	public ImageView newShip(ShipClasses.Classes shipClass) {
		StringBuilder spritePath = new StringBuilder(GlobalVars.SHIP_SPRITE_PATH);
		Image spriteImage;
		
		switch(shipClass) {
		case AKIRA:
			spritePath.append("akira.png");
			break;
		case EXCELSIOR:
			spritePath.append("excelsior.png");
			break;
		
		case INTREPID:
			spritePath.append("intrepid.png");
			break;
		}
		spriteImage = new Image(spritePath.toString());
		
		ImageView sprite = new ImageView(spriteImage);
		sprite.setLayoutX(-1*(spriteImage.getWidth()/2));
		sprite.setLayoutY(-1*(spriteImage.getHeight()/2));
		
		return sprite;
	}

}
