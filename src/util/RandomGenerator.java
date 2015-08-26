package util;

import java.util.Random;

import javafx.scene.paint.Color;
import location.Location;

/**
 * The singleton RandomGenerator class handles the generation of all random values
 * @author Jason Meredith
 *
 */
public class RandomGenerator {
	/** Singleton instance of itself **/
	public static RandomGenerator instance = new RandomGenerator();
	
	
	private Random random;
	
	public RandomGenerator() {
		random = new Random();
	}
	
	public Location getLocationInCircle(Location center, double radius) {
		Location result;
		
		double angle = getDouble(360);
		double distanceFromCenter = getDouble(radius);
		
		result = Calc.instance.angleDestination(center, angle, distanceFromCenter);
		
		return result;
	}
	
	public Color getColor() {
		double r = getDouble(1);
		double g = getDouble(1);
		double b = getDouble(1);
		
		return new Color(r, g, b, 1);
	}
	
	public double getDouble(double upperLimit) {
		return random.nextDouble()*upperLimit;
	}
	
	public double getDouble(double upperLimit, double lowerLimit) {
		double number;
		double range = upperLimit - lowerLimit;
		
		number = random.nextDouble()*range;

		
		return number+lowerLimit;
	}
	
	public double getDouble(double upperLimit, double lowerLimit, double interval) {
		double number = getDouble(upperLimit, lowerLimit);
		
		return number - (number % interval);
	}
	
	public double getDoubleOutsideRange(double upperLimit, double lowerLimit, double boundUp, double boundDown) {
		double number;
		
		do {
			number = getDouble(upperLimit, lowerLimit);
		} while(number < boundUp && number > boundDown);
		
		return number;
	}
	
	public boolean getBool() {
		return random.nextBoolean();
	}
	
	public int getInt(int upperLimit) {
		return random.nextInt(upperLimit+1);
	}
	
	public int getInt(int upperLimit, int lowerLimit) {
		int number;
		
		number = random.nextInt(upperLimit+1);
		
		while(number < lowerLimit) {
			number = random.nextInt(upperLimit+1);
		}
		
		return number;	
	}
	
	public int lowRandomInt(int max, int min) {
		double number;
		int range = max-min;
		
		do {
			number = Math.abs(random.nextGaussian());
		} while(number >= 4);
		
		number /= 4;
		number *= range;
		number += min;
		
		return (int) number;
		
	}

}
