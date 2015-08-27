package economy;

import cargo.CargoType;

public class ExchangeRates {
	
	public static double IronRate = 10;
	
	public static double getValue(CargoType type) {
		switch(type) {
		case IRON:
			return IronRate;
		}
		return 0;
	}

}
