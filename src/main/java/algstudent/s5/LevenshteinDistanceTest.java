package algstudent.s5;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LevenshteinDistanceTest {
	
	private LevenshteinDistance ld;
	
	@Before
	public void setUp() {
		ld = new LevenshteinDistance();
	}

	@Test
	public void testSolution() {
		assertEquals(ld.getSolution(), 7);
	}

}
