package util;

import location.Coord;
import location.Location;

public class Calc {
	
	public static Calc instance = new Calc();
	
	public double getDistance(double x1, double y1, double x2, double y2) {
		double x = Math.abs(x1 - x2);
		double y = Math.abs(y1 - y2);
		
		return Math.sqrt((x*x) + (y*y));
	}
	
	public double getDistance(Location pointA, Location pointB) {
		
		double x1 = pointA.getX();
		double y1 = pointA.getY();
		double x2 = pointB.getX();
		double y2 = pointB.getY();
		
		double x = Math.abs(x1 - x2);
		double y = Math.abs(y1 - y2);
		
		return Math.sqrt((x*x) + (y*y));
	}
	
	public double getAngle(Location pointA, Location pointB) {
		double xDistance = -1*(pointB.getX() - pointA.getX());
		double yDistance =  -1*(pointB.getY() - pointA.getY());
		
		return Math.atan2(yDistance, xDistance);
	}
	
	
	public Location angleDestination(Location source, double fireAngle, double distance) {
		double rise = Math.sin(fireAngle)*distance;
		double run = Math.cos(fireAngle)*distance;
		
		double newX = source.getX() - run;
		double newY = source.getY() - rise;
		
		//System.out.printf("\n\nFire angle: %.1f\nRise: %.1f\nRun: %.1f\nNew x: %.1f\nNew y: %.1f", fireAngle, rise,
		//		run, newX, newY);
		
		return new Coord(newX, newY);
	
	}
	
	
	public Location vectorDestination(Location source, Location click, double distance) {
		
		double xDistance = -1*(click.getX() - source.getX());
		double yDistance =  -1*(click.getY() - source.getY());
		double fireAngle = Math.atan2(yDistance, xDistance);
		
		return angleDestination(source, fireAngle, distance);
	}
	
}
