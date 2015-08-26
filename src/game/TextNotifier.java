package game;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class TextNotifier {

	private Group textGroup;
	private Label line1, line2, line3;
	
	private boolean line1Busy = false;
	private boolean line2Busy = false;
	private boolean line3Busy = false;
	
	public TextNotifier() {
		
		line1 = new Label();
		line2 = new Label();
		line3 = new Label();
		
		textGroup = new Group(line1, line2, line3);
	}
	
	public void add(String text) {
		if(line3.getText() != "") {
			line1.setText(line2.getText());
			line2.setText(line3.getText());
			line3.setText(text);
		}
	}
	
	public void add(String text, Color color) {
		//TODO add text to notifier of specified color
	}
	
	private void update() {
		//TODO scroll everything up
	}
}
