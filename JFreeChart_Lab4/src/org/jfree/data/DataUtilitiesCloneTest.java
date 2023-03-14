package org.jfree.data;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import org.jfree.data.DataUtilities;

public class DataUtilitiesCloneTest {
  @Test
  public void testCloneValidInput () {
    final double [][] toClone = {
      {1.1, 2.2}
    };

    double [][] result = DataUtilities.clone(toClone);
    assertArrayEquals("the cloned array should match the original one toClone[0]", result, toClone);
  }
}
