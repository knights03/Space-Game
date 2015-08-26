package faction;

import util.GlobalVars;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Faction {

	public enum FactionRank {
		//CORPORATION, WARBAND, GUILD
		
		//Economic
		PARTNERSHIP, STARTUP, ENDEAVOUR, BUSINESS, CORPORATION,
		CONGLOMERATE, ENTERPRISE, MONOPOLY,
		
		//Military
		CLAN, POSSE, WARBAND, BRIGADE, FLEET, LEGION, ARMY, NATION,
		
		//Religious
		CULT, FOLLOWING, MOVEMENT, CHURCH
	}
	
	public enum Specialty {
		ECONOMIC, MILITARY, RELIGION
	}
	
	public enum Rank {
		
		//Economic
		RECRUIT, WORKER, SUPERVISOR, MANAGER, JREXEC, SREXEC, VP, CEO,
		
		//Military
		PRIVATE, CORPORAL, SERGEANT, LIEUTENANT, CAPTAIN, MAJOR, GENERAL, ADMIRAL,
		
		//Religious
		BELIEVER, FOLLOWER, POSTULANT, ACOLYTE, PRIEST, BISHOP, CARDINAL, POPE
	}
	
	private String name;
	private int xp;
	
	private Color primaryColour, secondaryColour;
	
	private Specialty factionSpecialty;
	private FactionRank factionRank;
	
	private ArrayList<Faction> allies = new ArrayList<Faction>();
	private ArrayList<Faction> enemies = new ArrayList<Faction>();
	
	public Faction(String name, Color primary, Color secondary) {
		this.name = name;
		this.primaryColour = primary;
		this.secondaryColour = secondary;
	}
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}
	/**
	 * @return the primaryColour
	 */
	public Color getPrimaryColour() {
		return primaryColour;
	}
	/**
	 * @return the secondaryColour
	 */
	public Color getSecondaryColour() {
		return secondaryColour;
	}
	/**
	 * @return the factionSpecialty
	 */
	public Specialty getFactionSpecialty() {
		return factionSpecialty;
	}
	/**
	 * @return the factionRank
	 */
	public FactionRank getFactionRank() {
		return factionRank;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}
	/**
	 * @param primaryColour the primaryColour to set
	 */
	public void setPrimaryColour(Color primaryColour) {
		this.primaryColour = primaryColour;
	}
	/**
	 * @param secondaryColour the secondaryColour to set
	 */
	public void setSecondaryColour(Color secondaryColour) {
		this.secondaryColour = secondaryColour;
	}
	/**
	 * @param factionSpecialty the factionSpecialty to set
	 */
	public void setFactionSpecialty(Specialty factionSpecialty) {
		this.factionSpecialty = factionSpecialty;
	}
	/**
	 * @param factionRank the factionRank to set
	 */
	public void setFactionRank(FactionRank factionRank) {
		this.factionRank = factionRank;
	}
	
	/**
	 * @return the allies
	 */
	public ArrayList<Faction> getAllies() {
		return allies;
	}


	/**
	 * @return the enemies
	 */
	public ArrayList<Faction> getEnemies() {
		return enemies;
	}


	/**
	 * @param allies the allies to set
	 */
	public void setAllies(ArrayList<Faction> allies) {
		this.allies = allies;
	}


	/**
	 * @param enemies the enemies to set
	 */
	public void setEnemies(ArrayList<Faction> enemies) {
		this.enemies = enemies;
	}
	
	public void addAlly(Faction faction) {
		allies.add(faction);
		
		if(enemies.contains(faction)) {
			enemies.remove(faction);
		}
	}
	
	public void addEnemy(Faction faction) {
		enemies.add(faction);
		
		if(allies.contains(faction)) {
			allies.remove(faction);
		}
	}
	
	public void makeNeutral(Faction faction) {
		if(allies.contains(faction)) {
			allies.remove(faction);
		}
		
		if(enemies.contains(faction)) {
			enemies.remove(faction);
		}
	}


	public void addXP(int addAmount) {
		this.xp += addAmount;
		adjustRanking();
	}
	
	private void adjustRanking() {
		if(factionSpecialty == Specialty.ECONOMIC) {
			if(xp < GlobalVars.FACTION_ECOMIL_1) {
				factionRank = FactionRank.PARTNERSHIP;
			} else if(xp >= GlobalVars.FACTION_ECOMIL_1 && xp < GlobalVars.FACTION_ECOMIL_2) {
				factionRank = FactionRank.STARTUP;
			}
		}
	}
	
	
}
