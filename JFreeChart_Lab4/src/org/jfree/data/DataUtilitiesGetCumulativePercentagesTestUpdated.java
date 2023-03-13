package org.jfree.data;

import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Comparable;
import java.lang.Number;

import java.security.InvalidParameterException;

public class DataUtilitiesGetCumulativePercentagesTestUpdated {
  // Nonsequential
  @Test
  public void testCumulativePercentagesNonsequentialValidInput() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(1, 3, 5, 6, 7);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(5));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(1));
        allowing(values).getKey(1);
        will(returnValue(3));
        allowing(values).getKey(2);
        will(returnValue(5));
        allowing(values).getKey(3);
        will(returnValue(6));
        allowing(values).getKey(4);
        will(returnValue(7));

        allowing(values).getValue(1);
        will(returnValue(1));
        allowing(values).getValue(3);
        will(returnValue(2));
        allowing(values).getValue(5);
        will(returnValue(3));
        allowing(values).getValue(6);
        will(returnValue(3));
        allowing(values).getValue(7);
        will(returnValue(2));
      }
    });

    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
    // final List<Number> expectedValues = Arrays.asList(
    //     0.09090909090909091,
    //     0.2727272727272727,
    //     0.5454545454545454,
    //     0.8181818181818182,
    //     1.0);

    final double sum = 1+2+3+3+2;
    final List<Number> expectedValues = Arrays.asList(
        1/sum,
        (1+2)/sum,
        (1+2+3)/sum,
        (1+2+3+3)/sum,
        (1+2+3+3+2)/sum);

    List<Number> actualValues = new ArrayList<Number>();

    for(int i = 0; i < keys.size(); i++) {
      // copy all values into comparable list
      Comparable key = keys.get(i);
      actualValues.add(actual.getValue(key));
    }

     assertSame(
         "Testing getCumulativePercentages with valid KeyedValues instance",
         expectedValues,
         actualValues);
   }// */

  // An adjustment for sequential keys
  @Test
  public void testCumulativePercentagesSequentialValidInput() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(0, 1, 2, 3, 4);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(5));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(0));
        allowing(values).getKey(1);
        will(returnValue(1));
        allowing(values).getKey(2);
        will(returnValue(2));
        allowing(values).getKey(3);
        will(returnValue(3));
        allowing(values).getKey(4);
        will(returnValue(4));

        allowing(values).getValue(0);
        will(returnValue(1));
        allowing(values).getValue(1);
        will(returnValue(2));
        allowing(values).getValue(2);
        will(returnValue(3));
        allowing(values).getValue(3);
        will(returnValue(3));
        allowing(values).getValue(4);
        will(returnValue(2));
      }
    });

    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
    // final List<Number> expectedValues = Arrays.asList(
    //     0.09090909090909091,
    //     0.2727272727272727,
    //     0.5454545454545454,
    //     0.8181818181818182,
    //     1.0);

    final double sum = 1+2+3+3+2;
    final List<Number> expectedValues = Arrays.asList(
        1/sum,
        (1+2)/sum,
        (1+2+3)/sum,
        (1+2+3+3)/sum,
        (1+2+3+3+2)/sum);

    List<Number> actualValues = new ArrayList<Number>();

    for(int i = 0; i < keys.size(); i++) {
      // copy all values into comparable list
      Comparable key = keys.get(i);
      actualValues.add(actual.getValue(key));
    }

    assertSame(
        "Testing getCumulativePercentages with valid KeyedValues instance",
        expectedValues,
        actualValues);
  } // */

  @Test
  public void testCumulativePercentagesSequentialValidInputWithNull() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(0, 1, 2, 3, 4);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(5));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(0));
        allowing(values).getKey(1);
        will(returnValue(1));
        allowing(values).getKey(2);
        will(returnValue(2));
        allowing(values).getKey(3);
        will(returnValue(3));
        allowing(values).getKey(4);
        will(returnValue(4));

        allowing(values).getValue(0);
        will(returnValue(1));
        allowing(values).getValue(1);
        will(returnValue(2));
        allowing(values).getValue(2);
        will(returnValue(null));
        allowing(values).getValue(3);
        will(returnValue(3));
        allowing(values).getValue(4);
        will(returnValue(2));
      }
    });

    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
    // final List<Number> expectedValues = Arrays.asList(
    //     0.09090909090909091,
    //     0.2727272727272727,
    //     0.5454545454545454,
    //     0.8181818181818182,
    //     1.0);

    final double sum = 1+2+3+3+2;
    final List<Number> expectedValues = Arrays.asList(
        1/sum,
        (1+2)/sum,
        (1+2+3)/sum,
        (1+2+3+3)/sum,
        (1+2+3+3+2)/sum);

    List<Number> actualValues = new ArrayList<Number>();

    for(int i = 0; i < keys.size(); i++) {
      // copy all values into comparable list
      Comparable key = keys.get(i);
      actualValues.add(actual.getValue(key));
    }

    assertSame(
        "Testing getCumulativePercentages with valid KeyedValues instance",
        expectedValues,
        actualValues);
  } // */


  @Test
  public void testCumulativePercentagesSequentialInvalidItemCount() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(0, 1, 2, 3, 4);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(-1));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(0));
        allowing(values).getKey(1);
        will(returnValue(1));
        allowing(values).getKey(2);
        will(returnValue(2));
        allowing(values).getKey(3);
        will(returnValue(3));
        allowing(values).getKey(4);
        will(returnValue(4));

        allowing(values).getValue(0);
        will(returnValue(1));
        allowing(values).getValue(1);
        will(returnValue(2));
        allowing(values).getValue(2);
        will(returnValue(3));
        allowing(values).getValue(3);
        will(returnValue(3));
        allowing(values).getValue(4);
        will(returnValue(2));
      }
    });

    thrown.expect(InvalidParameterException.class);
    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
  } // */

  @Test
  public void testCumulativePercentagesSequentialInvalidItemCountWithNull() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(0, 1, 2, 3, 4);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(-1));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(0));
        allowing(values).getKey(1);
        will(returnValue(null));
        allowing(values).getKey(2);
        will(returnValue(2));
        allowing(values).getKey(3);
        will(returnValue(3));
        allowing(values).getKey(4);
        will(returnValue(4));

        allowing(values).getValue(0);
        will(returnValue(1));
        allowing(values).getValue(1);
        will(returnValue(2));
        allowing(values).getValue(2);
        will(returnValue(3));
        allowing(values).getValue(3);
        will(returnValue(3));
        allowing(values).getValue(4);
        will(returnValue(2));
      }
    });

    thrown.expect(InvalidParameterException.class);
    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
  } // */

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testCumulativePercentagesInvalidData() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(1, 3, 5, 6, 7);
    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(5));
        allowing(values).getKeys();
        will(returnValue(keys));

        allowing(values).getKey(0);
        will(returnValue(1));
        allowing(values).getKey(1);
        will(returnValue(3));
        allowing(values).getKey(2);
        will(returnValue(5));
        allowing(values).getKey(3);
        will(returnValue(6));
        allowing(values).getKey(4);
        will(returnValue(7));

        allowing(values).getValue(1);
        will(returnValue(1));
        allowing(values).getValue(5);
        will(returnValue(3));
        allowing(values).getValue(6);
        will(returnValue(3));
        allowing(values).getValue(7);
        will(returnValue(2));
      }
    });

    thrown.expect(InvalidParameterException.class);
    final KeyedValues actual = DataUtilities.getCumulativePercentages(values);
  }
}

/*/ unnecessary, but would be useful
private class TestKeyedValues implements KeyedValues {
  private List<Comparable> keys;
  private List<Number> values;

  public TestKeyedValues(List<Comparable> keys, List<Comparable> values) {
    this.keys = keys;
    this.values = values;
  }

  public int getIndex(Comparable key) {
    return keys.indexOf(key);
  }

  public Comparable getKey(int index) {
    return keys.get(index);
  }

  public List<Comparable> getKeys() {
    return this.keys;
  }

  public Number getValue(Comparable key) {
    int index = this.getIndex(key);
    return this.values.get(index);
  }
} // */

