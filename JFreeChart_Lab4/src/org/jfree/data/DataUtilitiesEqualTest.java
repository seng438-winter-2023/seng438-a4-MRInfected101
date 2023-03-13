package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Rule;

import org.jfree.data.DataUtilities;

public class DataUtilitiesEqualTest {
  @Test
  public void testEqualSame() {
    double [][] a = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    double [][] b = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    boolean result = DataUtilities.equal(a, b);
    assertTrue("2d arrays should be equal", result);
  }

  @Test
  public void testEqualDifferentValues() {
    double [][] a = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    double [][] b = {
      {1.1, 2.2},
      {3.3, 4.4, 6.6}
    };

    boolean result = DataUtilities.equal(a, b);
    assertFalse("2d arrays differ in value", result);
  }

  @Test
  public void testEqualDifferentInnerSize() {
    double [][] a = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    double [][] b = {
      {1.1, 2.2},
      {3.3, 4.4}
    };

    boolean result = DataUtilities.equal(a, b);
    assertFalse("2d arrays differ in size", result);
  }

  @Test
  public void testEqualDifferentOuterSize() {
    double [][] a = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    double [][] b = {
      {1.1, 2.2},
      {3.3, 4.4},
      {5.5}
    };

    boolean result = DataUtilities.equal(a, b);
    assertFalse("2d arrays differ in size", result);
  }

  @Test
  public void testEqualFirstNull() {
    double [][] a = null;

    double [][] b = {
      {1.1, 2.2},
      {3.3, 4.4}
    };

    boolean result = DataUtilities.equal(a, b);
    assertFalse("First argument is null", result);
  }

  @Test
  public void testEqualSecondNull() {
    double [][] a = {
      {1.1, 2.2},
      {3.3, 4.4, 5.5}
    };

    double [][] b = null;

    boolean result = DataUtilities.equal(a, b);
    assertFalse("Second argument is null", result);
  }

  @Test
  public void testEqualBothNull() {
    double [][] a = null;

    double [][] b = null;

    boolean result = DataUtilities.equal(a, b);
    assertTrue("Both arguments are null", result);
  }
}
