package kr.or.javacafe.bingo.app.common;

public class RandomUtil {

	
	public static int[] getBaseArray(int length) {
		int[] result = new int[length];
		for (int i=0; i<length; i++) {
			result[i] = i+1;
		}
		
		return result;
	}
	

	
	public static int[] shuffle(int[] array) {
		int n = array.length;
		for (int i = 0; i < array.length; i++) {
		    // Get a random index of the array past i.
		    int random = i + (int) (Math.random() * (n - i));
		    // Swap the random element with the present element.
		    int randomElement = array[random];
		    array[random] = array[i];
		    array[i] = randomElement;
		}
		
		return array;
	}
	
	
	
}


