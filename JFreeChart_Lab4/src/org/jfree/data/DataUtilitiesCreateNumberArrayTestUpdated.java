package org.jfree.data;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

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

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  /**
   * 
   * Receiving expected exception from method
   * 
   */
  @Test
  public void createNumberArrayInvalid() {
    double invalidArray[] = null;

    thrown.expect(InvalidParameterException.class);
    Number generatedArray[] = DataUtilities.createNumberArray(invalidArray);
  }

  /**
   * 
   * Testing empty array
   * 
   */
  @Test
  public void createNumberArrayEmpty() {
    double emptyArray[] = {};
    Number expectedNumberArray[] = {};
    Number generatedArray[] = DataUtilities.createNumberArray(emptyArray);

    assertArrayEquals(expectedNumberArray, generatedArray);
  }
}
