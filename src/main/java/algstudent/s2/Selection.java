package algstudent.s2;

/* This program can be used to sort n elements with 
 * a "bad" algorithm (quadratic). 
 * It is the SELECTION */
public class Selection extends Vector {
	public Selection(int nElements) {
		super(nElements);
	}

	@Override
	public void sort() {
		for (int i = 0; i < elements.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < elements.length; j++) {
				if (elements[j] < elements[index]) {
					index = j;// searching for lowest index
				}
			}
			int smallerNumber = elements[index];
			elements[index] = elements[i];
			elements[i] = smallerNumber;
		}
	}

	@Override
	public String getName() {
		return "Selection";
	}
}
