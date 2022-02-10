package cmsc256;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
// import cmsc256.MyIndexOutOfBoundsException;
// import cmsc256.RamString;
// import cmsc256.WackyStringInterface;


public class RamStringSampleTest {
	 WackyStringInterface normalString;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		normalString = new RamString("Computer Science @ VCU Spring 2021");
	}

	// test if the constructor properly sets the value of the string
	@Test
	public void testRamStringParameterizedConstructor1() {
		assertTrue("Computer Science @ VCU Spring 2021".equals(normalString.getWackyString()));
	}

	// test if the constructor correctly throws the exception for a null string
	@Test (expected = IllegalArgumentException.class)
	public void testRamStringParameterizedConstructor2() {
		RamString tryNull = new RamString(null);
	}

	// test the countDoubleDigits with a string containing 0 double digits
	@Test
	public void testCountDoubleDigits1() {
		assertEquals(0, normalString.countDoubleDigits());
	}

	// test the countDoubleDigits with a string containing 1 double digit pair
	@Test
	public void testCountDoubleDigits2() {
		RamString temp = new RamString("Computer Science @ VCU 50 g00d!");
		assertEquals(1, temp.countDoubleDigits());
	}
}
