package org.jfree.data;

import org.jmock.Mockery;
import static org.junit.Assert.*;
import org.junit.Test;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;

import org.junit.rules.ExpectedException;
import org.junit.Rule;

import java.security.InvalidParameterException;

public class DataUtilitiesCalculateColumnTotalTestUpdated {
	@Test
	public void calculateColumnTotalForTwoValues() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0);
		// verify
		assertEquals(10.0, result, .000000001d);
		// tear-down: NONE in this test method
	}

  @Test
  public void testColumnTotalValidInputsWithNull() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(2));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(1, 0);
        will(returnValue(null));
      }
    });

    double result = DataUtilities.calculateColumnTotal(values, 0);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        3.4,
        result,
        .000000001d
    );
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

	/**
	 * Throwing a org.jmock.api.ExpectationError instead of the expected
	 * java.lang.IndexOutOfBoundsException that should be thrown because the array
	 * is of size 3 and we are trying to access a value that would only be
	 * accessible in an array of size 4 or greater
	 * 
	 */
	@Test
	public void calculateColumnTotalForInvalidValues2D() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
      {
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 0);
				will(returnValue(6.0));
				one(values).getValue(1, 0);
				will(returnValue(5));
				one(values).getValue(2, 0);
				will(throwException(new IndexOutOfBoundsException()));
      }
		});

		thrown.expect(InvalidParameterException.class);
		double result = DataUtilities.calculateColumnTotal(values, 0);
	}

	/**
	 * Failure trace says that it expects the value of result to be 5.5 but was 0.0,
	 * which is expected because of the null input, but I am not sure as to why it
	 * is expecting a result of 5.5 when null added with any value is supposed to
	 * output 0 according to the documentation
	 */

	@Test
	public void calculateColumnTotalForInvalidValuesWithNull() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(10));
				one(values).getValue(0, 0);
				will(returnValue(4.5));
				one(values).getValue(1, 0);
				will(returnValue(null));
				one(values).getValue(2, 0);
				will(throwException(new IndexOutOfBoundsException()));
			}
		});

    thrown.expect(InvalidParameterException.class);
		double result = DataUtilities.calculateColumnTotal(values, 0);
	}

  @Test
  public void testColumnTotalInvalidRowCount() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(-1));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(2.2));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateColumnTotal(values, 0);
  }

  @Test
  public void testColumnTotalInvalidRowCountWithNull() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(-1));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(null));
        one(values).getValue(2, 0);
        will(returnValue(new InvalidParameterException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateColumnTotal(values, 0);
  }
  
  @Test
  public void testColumnTotalNullInput() {
	  final Values2D value = null;
	  thrown.expect(InvalidParameterException.class);
	  DataUtilities.calculateColumnTotal(value, 0);
  }

  /* test overridden method definition */
  @Test
  public void calculateColumnTotalForTwoValuesValidRows() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
    int[] validRows = {0, 1};
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
			}
		});
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
		// verify
		assertEquals(10.0, result, .000000001d);
  }

  @Test
  public void testColumnTotalValidInputsWithNullValidRows() {
    Mockery mockingContext = new Mockery();
    int[] validRows = {0,1};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(2));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(1, 0);
        will(returnValue(null));
      }
    });

    double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        3.4,
        result,
        .000000001d
    );
  }

	/**
	 * Throwing a org.jmock.api.ExpectationError instead of the expected
	 * java.lang.IndexOutOfBoundsException that should be thrown because the array
	 * is of size 3 and we are trying to access a value that would only be
	 * accessible in an array of size 4 or greater
	 * 
	 */
	@Test
	public void calculateColumnTotalForInvalidValues2DValidRows() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
    int[] validRows = {0,1,2,3,4,5,6,7,8};
		mockingContext.checking(new Expectations() {
      {
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 0);
				will(returnValue(6.0));
				one(values).getValue(1, 0);
				will(returnValue(5));
				one(values).getValue(2, 0);
				will(throwException(new IndexOutOfBoundsException()));
			}
		});

		thrown.expect(InvalidParameterException.class);
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	}

	/**
	 * 
	 * Test fails but produces the expected output of a IndexOutOfBoundsExpectation
	 * 
	 */

	

	/**
	 * Failure trace says that it expects the value of result to be 5.5 but was 0.0,
	 * which is expected because of the null input, but I am not sure as to why it
	 * is expecting a result of 5.5 when null added with any value is supposed to
	 * output 0 according to the documentation
	 */

	@Test
	public void calculateColumnTotalForInvalidValuesWithNullValidRows() {
		Mockery mockingContext = new Mockery();
    int[] validRows = {0,1,2,3,4,5,6,7,8,9};
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(10));
				one(values).getValue(0, 0);
				will(returnValue(4.5));
				one(values).getValue(1, 0);
				will(returnValue(null));
				one(values).getValue(2, 0);
				will(throwException(new IndexOutOfBoundsException()));
			}
		});

    thrown.expect(InvalidParameterException.class);
		double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	}

  @Test
  public void testColumnTotalInvalidRowCountValidRows() {
    Mockery mockingContext = new Mockery();
    int[] validRows = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(-1));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(2.2));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateColumnTotal(values, 0, validRows);
  }

  @Test
  public void testColumnTotalInvalidRowCountWithNullValidRows() {
    Mockery mockingContext = new Mockery();
    int[] validRows = {0,1,2,3,4,5,6,7,8,9};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getRowCount();
        will(returnValue(10));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(null));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateColumnTotal(values, 0, validRows);
  }
  
  @Test
  public void testColumnTotalNullInputValidRows() {
	  final Values2D value = null;
    int[] validRows = {0};

	  thrown.expect(InvalidParameterException.class);
	  DataUtilities.calculateColumnTotal(value, 0, validRows);
  }
}
