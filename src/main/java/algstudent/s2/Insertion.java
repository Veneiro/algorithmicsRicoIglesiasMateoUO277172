package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the DIRECT INSERTION */
public class Insertion extends Vector {
	public Insertion(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		int n = elements.length;
		for (int j = 1; j < n; j++) {
			int key = elements[j];
			int i = j - 1;
			while ((i > -1) && (elements[i] > key)) {
				elements[i + 1] = elements[i];
				i--;
			}
			elements[i + 1] = key;
		}
	}

	@Override
	public String getName() {
		return "Insertion";
	}
}
