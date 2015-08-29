package game;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TextNotifier {

	private int numberOfLines;
	
	private Label[] textLines;
	
	private VBox notifierText = new VBox();
	
	public TextNotifier(int lines) {
		numberOfLines = lines;
		
		textLines = new Label[numberOfLines];
		
		for(int i = 0; i < numberOfLines; i++) {
			textLines[i] = new Label();
			notifierText.getChildren().add(textLines[i]);
		}
	}
	
	private void shiftTextDown() {
		for(int i = numberOfLines-1; i >= 1; i--) {
				textLines[i].setText(textLines[i-1].getText());
				textLines[i].setTextFill(textLines[i-1].getTextFill());
			
		}
	}
	
	public void addText(String text) {
		addText(text, Color.WHITE);
	}
	
	public void addText(String text, Color color) {
		shiftTextDown();
		textLines[0].setText(text);
		textLines[0].setTextFill(color);
	}
	
	public VBox getNotifierText() {
		return notifierText;
	}
	
	
}
