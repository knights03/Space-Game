package util;

import javafx.scene.paint.Color;

public class GlobalVars {
	
	public static double GAME_SCREEN_HEIGHT = 900;
	public static double GAME_SCREEN_WIDTH = 1480;

	public static double WORLD_SIZE_X = 12000;
	public static double WORLD_SIZE_Y = 12000;
	
	public static double STAR_DENSITY = 100;
	public static double STAR_AMOUNT = ((WORLD_SIZE_X * WORLD_SIZE_Y)/144000000) * STAR_DENSITY;
	
	public static String MAIN_WINDOW_TITLE = "GD Main";
	public static double MAIN_WINDOW_HEIGHT = 900;
	public static double MAIN_WINDOW_WIDTH = 1480;
	
	public static String MAP_WINDOW_TITLE = "Galactic Map";
	public static double MAP_WINDOW_HEIGHT = 700;
	public static double MAP_WINDOW_WIDTH = 700;
	public static Color MAP_WINDOW_BG_COLOUR = Color.rgb(7, 5, 38);
	
	public static String NEW_COURSE_WINDOW_TITLE = "Set New Course";
	public static double NEW_COURSE_WINDOW_HEIGHT = 500;
	public static double NEW_COURSE_WINDOW_WIDTH = 500;
	public static Color NEW_COURSE_WINDOW_BG_COLOUR = Color.rgb(7, 5, 38);		
	
	public static String UNIT_INFO_WINDOW_TITLE = "Unit Info";
	public static double UNIT_INFO_WINDOW_HEIGHT = 500;
	public static double UNIT_INFO_WINDOW_WIDTH = 500;
	public static Color UNIT_INFO_WINDOW_BG_COLOUR = Color.rgb(7, 5, 38);
	
	public static double MIN_LOCATION_SEPERATION = 12;
	
	
	
	public static int FACTION_ECOMIL_1 = 100;
	public static int FACTION_ECOMIL_2 = 500;
	public static int FACTION_ECOMIL_3 = 3000;
	public static int FACTION_ECOMIL_4 = 12000;
	public static int FACTION_ECOMIL_5 = 50000;
	public static int FACTION_ECOMIL_6 = 150000;
	public static int FACTION_ECOMIL_7 = 400000;
	public static int FACTION_ECOMIL_8 = 1000000;
	
	public static int FACTION_REL_1 = 100;
	public static int FACTION_REL_2 = 1000;
	public static int FACTION_REL_3 = 100000;
	public static int FACTION_REL_4 = 1500000;
	
	public static int SMALL_MARKET = 10;
	public static int MEDIUM_MARKET = 15;
	public static int LARGE_MARKET = 30;
	public static int HUGE_MARKET = 100;
	
	public static int SMALL_HQ_MEMBER_RATE = 1;
	public static int MEDIUM_HQ_MEMBER_RATE = 3;
	public static int LARGE_HQ_MEMBER_RATE = 10;
	public static int HUGE_HQ_MEMBER_RATE = 20;
	
}
