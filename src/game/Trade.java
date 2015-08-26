package game;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import unit.Unit;

public class Trade {

	private Player player;
	private Unit npc;
	
	private ArrayList<Item> playerItems = new ArrayList<Item>();
	private ArrayList<Item> npcItems = new ArrayList<Item>();
	
	private boolean tradeSuccess;
	
	Label successNotifier = new Label("");
	
	public Trade(Player player, Unit npc) {
		this.player = player;
		this.npc = npc;
	}

	public void addRemovePlayerItem(Item item) {
		if(playerItems.contains(item)) {
			playerItems.remove(item);
		} else {
			playerItems.add(item);
		}
	}
	
	public void addRemoveNpcItem(Item item) {
		if(npcItems.contains(item)) {
			npcItems.remove(item);
		} else {
			npcItems.add(item);
		}
	}
	
	public boolean attemptTrade() {
		
		boolean success = false;
		
		int playerItemsValue = 0;
		int npcItemsValue = 0;
		
		double npcTradeLeniency = npc.getTradeLeniency();
		
		for(Item item : playerItems) {
			playerItemsValue += item.getCost();
		}
		
		for(Item item : npcItems) {
			npcItemsValue =+ item.getCost();
		}
		
		double itemsValueDifferencePercent = (Math.abs(playerItemsValue - npcItemsValue)/npcItemsValue)*100;
		
		if(itemsValueDifferencePercent <= npcTradeLeniency) {
			success = true;
		}
		
		return success;
	}
	
	public Scene showTradeScreen(Unit unit) {
		VBox npcItemsBox = new VBox();
		VBox playerItemsBox = new VBox();
		
		HBox tradeScreenRoot = new HBox(npcItemsBox, playerItemsBox);
		Scene tradeScreen = new Scene(tradeScreenRoot);
		
		tradeScreenRoot.setSpacing(15);
		
		
		Label[] npcItemsLabel = new Label[unit.getItems().size()];
		for(int i = 0; i < unit.getItems().size(); i++) {
			
			Item currentItem = unit.getItems().get(i);
			
			
			npcItemsLabel[i] = new Label(currentItem.getName());
			npcItemsBox.getChildren().add(npcItemsLabel[i]);
			
			Label currentLabel = npcItemsLabel[i];
			
			npcItemsLabel[i].setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					if(currentLabel.getTextFill() == Color.GREEN) {
						currentLabel.setTextFill(Color.BLACK);
					} else {
						currentLabel.setTextFill(Color.GREEN);
					}
					
					addRemoveNpcItem(currentItem);
				}
				
			});
			
		}
		
		Label[] playerItemsLabel = new Label[player.getItems().size()];
		for(int i = 0; i < player.getItems().size(); i++) {
			
			Item currentItem = player.getItems().get(i);
			
			
			//playerItemsLabel[i] = new Label(currentItem.getName());
			playerItemsLabel[i] = new Label("Asdf");
			playerItemsBox.getChildren().add(playerItemsLabel[i]);
			
			Label currentLabel = playerItemsLabel[i];
			
			playerItemsLabel[i].setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					if(currentLabel.getTextFill() == Color.GREEN) {
						currentLabel.setTextFill(Color.BLACK);
					} else {
						currentLabel.setTextFill(Color.GREEN);
					}
					
					addRemoveNpcItem(currentItem);
				}
				
			});
			
		}
		
		
		Button attemptTrade = new Button("Attempt Trade");
		
		attemptTrade.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setTradeSuccess(attemptTrade());
			}
			
		});
		
		npcItemsBox.getChildren().add(attemptTrade);
		npcItemsBox.getChildren().add(successNotifier);
		
		return tradeScreen;
		
	}
	
	private void setTradeSuccess(boolean success) {
		tradeSuccess = success;
		
		if(success) {
			successNotifier.setTextFill(Color.GREEN);
			successNotifier.setText("Trade accepted");
		}
		
		if(!success) {
			successNotifier.setTextFill(Color.RED);
			successNotifier.setText("Trade terms unacceptable");
		}
	}
}
