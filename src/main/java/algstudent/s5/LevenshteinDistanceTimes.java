/**
 * 
 */
package algstudent.s5;

import java.util.Random;

/**
 * @author UO277172
 *
 */
public class LevenshteinDistanceTimes {

	private static LevenshteinDistance ld = new LevenshteinDistance();;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("NORMAL SIZE: ");
		doTimesNormalSize();
		
		System.out.println();
		
		System.out.println("INCREASING SIZE: ");
		doTimesIncreasedSize();	
	}
	
	private static void doTimesNormalSize() {
		long a = System.currentTimeMillis();
		ld.getSolution();
		long b = System.currentTimeMillis();
		System.out.println("TIME: " + (b - a) + " ms");
	}
	
	private static void doTimesIncreasedSize() {
		
		for (int i = 100; i < Integer.MAX_VALUE; i*=2) {
			String str1 = createRandomWord(i);
			String str2 = createRandomWord(i);
			ld.setA(str1);
			ld.setB(str2);
			long a = System.currentTimeMillis();
			ld.getSolution();
			long b = System.currentTimeMillis();
			System.out.println("N: " + i + " TIME: " + (b - a) + " ms");
		}
	}
	
	private static String createRandomWord(int size) {
		char[] word = new char[size];
		Random r = new Random();
		
		for (int i = 0; i < word.length; i++) {
			word[i] = (char)r.nextInt(90);
			while((int)word[i] < 65) {
				word[i] = (char)r.nextInt(90);
			}
		}
		return String.valueOf(word);
	}
}
