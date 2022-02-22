package algstudent.s1;

public class Vector2 {
	public static void main(String arg[]) {
		long inicio = System.currentTimeMillis();
		Vector3.main(arg, 5);
		Vector1.main(arg);
		long fin = System.currentTimeMillis();

		double tiempo = fin - inicio;

		System.out.println(tiempo);
	}
}
