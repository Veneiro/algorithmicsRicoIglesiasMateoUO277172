package algstudent.s1;

import java.util.Random;

public class Vector3 {
	static Random coin = new Random();

	public static String[] main(String[] arg, int w) {
		String newarr[] = new String[w * arg.length];
		for (int j = 0; j < w * arg.length; j++) {
			newarr[j] = String.valueOf(coin.nextInt(1000));
		}
		arg = newarr;
		return arg;
	}
}
