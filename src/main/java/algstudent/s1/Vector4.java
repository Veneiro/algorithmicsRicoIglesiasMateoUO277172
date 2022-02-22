package algstudent.s1;

public class Vector4 {

	public static void main(String arg[]) {
		int times = Integer.parseInt(arg[0]);
		long timeBeforeSum, timeAfterSum;
		long timeBeforeFill, timeAfterFill;
		long timeBeforeMax, timeAfterMax;
		int totalSum = 0;
		int[] v;

		for (int i = 10; i < Integer.MAX_VALUE; i *= 3) {
			v = new int[i];
			timeBeforeFill = System.currentTimeMillis();
			for (int j = 0; j < times; j++) {
				Vector1.fillIn(v);
			}
			timeAfterFill = System.currentTimeMillis();
			timeBeforeSum = System.currentTimeMillis();
			for (int j = 1; j < times; j++) {
				totalSum = Vector1.sum(v);
			}
			timeAfterSum = System.currentTimeMillis();
			int m[] = new int[2];
			timeBeforeMax = System.currentTimeMillis();
			for (int j = 1; j < times; j++) {
				Vector1.maximum(v, m);
			}
			timeAfterMax = System.currentTimeMillis();

			long timeSum = timeAfterSum - timeBeforeSum;
			long timeFilling = timeAfterFill - timeBeforeFill;
			long timeMax = timeAfterMax - timeBeforeMax;

			System.out.println("Size: " + i + "\t | fillIn(t): " + timeFilling + "ms \t| sum(t): " + timeSum
					+ "ms \t| maximum(t): " + timeMax + "ms \t| Total Sum: " + totalSum);
		}
	}
}
