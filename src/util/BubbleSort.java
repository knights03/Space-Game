package util;

public class BubbleSort {

	public static BubbleSort instance = new BubbleSort();

	public int[] sort(int[] input) {
		int[] output = input.clone();
		
		for(int i = input.length; i > 0; i--) {
			for(int j = 1; j < i; j++) {
				int placeholder;
				if(output[j] < output[j-1]) {
					placeholder = output[j];
					output[j] = output[j-1];
					output[j-1] = placeholder;
				}
			}
		}
		
		return output;
	}
	
	public double[] sort(double[] input) {
		double[] output = input.clone();
		
		for(int i = input.length; i > 0; i--) {
			for(int j = 1; j < i; j++) {
				double placeholder;
				if(output[j] < output[j-1]) {
					placeholder = output[j];
					output[j] = output[j-1];
					output[j-1] = placeholder;
				}
			}
		}
		
		return output;
	}

}
