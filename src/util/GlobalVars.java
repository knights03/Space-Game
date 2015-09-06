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
	
	public static int TEXT_NOTIFIER_LINES = 4;
	
	public static double CLICK_AREA_OPACITY = 0;

	
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
	
	public static String PLANET_SPRITE_PATH = "file:images/planets/";
	public static String SHIP_SPRITE_PATH = "file:images/ships/";
	
	public static double ASTEROID_YIELD_MAX = 35;
	public static double ASTEROID_YIELD_MIN = 12;
	public static double IRON_ASTEROID_INT_MULT = 1;
	public static double TRITONITE_ASTEROID_INT_MULT = 3;

	
}
