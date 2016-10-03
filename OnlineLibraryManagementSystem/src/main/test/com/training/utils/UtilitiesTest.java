/**
 * 
 */
package com.training.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author 559207
 *
 */
public class UtilitiesTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Going to create object...");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		assertEquals("US111112", Utilities.idGenerator("US", "US111111"));
		assertEquals("MM00013", Utilities.idGenerator("MM", "MM00012"));
		assertEquals("MM100013", Utilities.idGenerator("MM", "MM100012"));
		assertEquals("LI123456", Utilities.idGenerator("L", "LL12344"));
	}

	@Test(expected = NumberFormatException.class)
	public void test() {
		assertEquals("MM1A00013", Utilities.idGenerator("MM", "MM1A0012"));
	}

}
