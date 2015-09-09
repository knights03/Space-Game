package ship;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ship.ShipClasses.Classes;
import util.GlobalVars;

public class ShipFactory {
	
	public static ShipFactory instance = new ShipFactory();
	
	public ImageView newShip(ShipClasses.Classes shipClass) {
		StringBuilder spritePath = new StringBuilder(GlobalVars.SHIP_SPRITE_PATH);
		Image spriteImage;
		
		for(Classes shipClassCheck : ShipClasses.Classes.values()) {
			if(shipClassCheck == shipClass) {
				spritePath.append(shipClass.toString().toLowerCase());
			}
		}
		
		spritePath.append(".png");
		
		spriteImage = new Image(spritePath.toString());
		
		ImageView sprite = new ImageView(spriteImage);
		sprite.setLayoutX(-1*(spriteImage.getWidth()/2));
		sprite.setLayoutY(-1*(spriteImage.getHeight()/2));
		
		return sprite;
	}

}
