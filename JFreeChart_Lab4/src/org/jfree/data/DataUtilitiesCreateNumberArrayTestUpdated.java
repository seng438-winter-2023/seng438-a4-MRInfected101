package org.jfree.data;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import org.junit.Test;
import org.jfree.data.DataUtilities;

public class DataUtilitiesCreateNumberArrayTestUpdated {

	/**
	 * 
	 * Seems like the createNumberArray() method is not functioning as intended;
	 * copying a null array according to the trace
	 * 
	 */
	@Test
	public void createNumberArrayValid() {
		double validArray[] = { 5.4, 6.2, 0.9, 7.8 };
		Number validNumberArray[] = { 5.4, 6.2, 0.9, 7.8 };
		Number generatedArray[] = DataUtilities.createNumberArray(validArray);

		assertArrayEquals(validNumberArray, generatedArray);
	}

	/**
	 * 
	 * Receiving expected exception from method
	 * 
	 */
	@Test(expected = InvalidParameterException.class)
	public void createNumberArrayInvalid() {
		double invalidArray[] = {};
		Number validNumberArray[] = {};
		Number generatedArray[] = DataUtilities.createNumberArray(invalidArray);

		assertArrayEquals(generatedArray, validNumberArray);

	}
}
