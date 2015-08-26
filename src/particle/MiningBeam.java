package particle;

import game.Game;
import game.Player;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import location.Location;
import util.RandomGenerator;

public class MiningBeam {

	private Line beam;
	
	private FadeTransition dissipate;
	
	public MiningBeam(Game game, Location target) {
		
		Location t = RandomGenerator.instance.getLocationInCircle(target, 7);
		
		beam = new Line(game.getPlayer().getLocation().getX(), game.getPlayer().getLocation().getY(),
				t.getX(), t.getY());
		
		beam.setStroke(game.getPlayer().getEquipedMiningTool().getBeamColor());
		
		
		
		game.getWorldGroup().getChildren().add(beam);
		
		beam.toBack();
		
		dissipate = new FadeTransition(Duration.millis(200), beam);
		
		dissipate.setFromValue(1);
		dissipate.setToValue(0);
		
		dissipate.play();
		
		dissipate.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				game.removeNode(beam);
			}
			
		});
	}

	/**
	 * @return the beam
	 */
	public Line getBeam() {
		return beam;
	}

	/**
	 * @param beam the beam to set
	 */
	public void setBeam(Line beam) {
		this.beam = beam;
	}
}
