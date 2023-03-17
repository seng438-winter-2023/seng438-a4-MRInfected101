package org.jfree.data;

import static org.junit.Assert.*;
import java.security.InvalidParameterException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.jfree.data.DataUtilities;

public class DataUtilitiesCreateNumberArray2DTestUpdated {

  /**
   * 
   * Method throws expected exception
   * 
   */

  @Test
  public void createNumberArray2DEmpty() {
    double invalid2D[][] = { {} };
    Number expectedNumber2D[][] = { {} };
    Number generatedArray[][] = DataUtilities.createNumberArray2D(invalid2D);

    assertArrayEquals(expectedNumber2D[0], generatedArray[0]);

  }
  /**
   * 
   * Method is not properly copying the valid2D array from double to a Number array type
   * 
   */
  @Test
  public void createNumberArray2DValidSymmetric() {
    double valid2D[][] = { { 6.0, 5.5, 2.1, 4.9 }, { 7.8, 5.6, 2.0, 4.4 } };
    Number validNumber2D[][] = { { 6.0, 5.5, 2.1, 4.9 }, { 7.8, 5.6, 2.0, 4.4 } };
    Number generatedArray[][] = DataUtilities.createNumberArray2D(valid2D);

    // assertTrue(java.util.Arrays.deepEquals(validNumber2D, generatedArray));
    assertArrayEquals(validNumber2D[0], generatedArray[0]);
    assertArrayEquals(validNumber2D[1], generatedArray[1]);
  }
  /**
   * 
   * Method is not properly copying the valid2D array from double to a Number array type
   * 
   */
  @Test
  public void createNumberArray2DValidAsymmetric() {
    double validAsymmetric2D[][] = { { 7.3, 5.8 }, { 2.0, 1.9, 9.3 }, { 3.4, 5.6 } };
    Number validNumberAsymmetricValid2D[][] = { { 7.3, 5.8 }, { 2.0, 1.9, 9.3 }, { 3.4, 5.6 } };
    Number generatedArray[][] = DataUtilities.createNumberArray2D(validAsymmetric2D);

    assertArrayEquals(validNumberAsymmetricValid2D[0], generatedArray[0]);
    assertArrayEquals(validNumberAsymmetricValid2D[1], generatedArray[1]);
    assertArrayEquals(validNumberAsymmetricValid2D[2], generatedArray[2]);
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void createNumberArray2DInnerNull() {
    double[][] invalid2DArray = {null};

    thrown.expect(InvalidParameterException.class);
    DataUtilities.createNumberArray2D(invalid2DArray);
  }

  @Test
  public void createNumberArray2DOuterNull() {
    double[][] invalid2DArray = null;

    thrown.expect(InvalidParameterException.class);
    DataUtilities.createNumberArray2D(invalid2DArray);
  }
}
