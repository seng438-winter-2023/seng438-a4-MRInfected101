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

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.lang.Comparable;
import java.lang.Number;

public class DataUtilitiesTest {
	@Test
	public void calculateColumnTotalForTwoValues() {
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(1, 0);
        will(returnValue(null));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(2.2));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
				one(values).getValue(0, 0);
				will(returnValue(7.5));
				one(values).getValue(1, 0);
				will(returnValue(2.5));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(1, 0);
        will(returnValue(null));
        one(values).getValue(2, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(-1, 0);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(2.2));
        one(values).getValue(0, 2);
        will(throwException(new IndexOutOfBoundsException()));
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
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(1,0);
        will(returnValue(null));
        one(values).getValue(2,0);
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

  @Test
  public void testRowTotalValidInputs() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(4.1));
        one(values).getValue(0, 2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    double result = DataUtilities.calculateRowTotal(values, 0);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        7.5,
        result,
        .000000001d
    );
  }

  @Test
  public void testRowTotalValidInputsWithNull() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(null));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    double result = DataUtilities.calculateRowTotal(values, 0);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        3.4,
        result,
        .000000001d
    );
  }

  @Test
  public void testRowTotalInvalidValues2D() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0);
  }

  @Test
  public void testRowTotalInvalidColumnCount() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(-1));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0, 2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0);
  }

  @Test
  public void testRowTotalInvalidColumnCountWithNull() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(-1));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(null));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0);
  }

  @Test
  public void testRowTotalInvalidRowIndex() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0, 2);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(10, 0);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10);
  }

  @Test
  public void testRowTotalAllInvalidParameters() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10)); // this makes it invalid as there are only 2
                               // columns
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(10,0);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });
    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10);
  }

  @Test
  public void testRowTotalNullInput() {
    int[] validColumns = {0};
    final Values2D values = null;

    thrown.expect(InvalidParameterException.class);
    DataUtilities.calculateRowTotal(values, 10, validColumns);
  }

  /* Testing the overridden method */
  @Test
  public void testRowTotalValidInputsValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0, 1};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(4.1));
        one(values).getValue(0, 2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    double result = DataUtilities.calculateRowTotal(values, 0, validColumns);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        7.5,
        result,
        .000000001d
    );
  }

  @Test
  public void testRowTotalValidInputsWithNullValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0, 1};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(null));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    double result = DataUtilities.calculateRowTotal(values, 0, validColumns);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        3.4,
        result,
        .000000001d
    );
  }

  @Test
  public void testRowTotalInvalidValues2DValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0, validColumns);
  }

  @Test
  public void testRowTotalInvalidColumnCountValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(-1));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0, validColumns);
  }

  @Test
  public void testRowTotalInvalidColumnCountWithNullValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(-1));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(null));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 0, validColumns);
  }

  @Test
  public void testRowTotalInvalidRowIndexValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(10,0);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });

    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10, validColumns);
  }

  @Test
  public void testRowTotalAllInvalidParametersValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10)); // this makes it invalid as there are only 2
                               // columns
        one(values).getValue(0, -1);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
        one(values).getValue(10,0);
        will(throwException(new IndexOutOfBoundsException()));
        one(values).getValue(0,2);
        will(throwException(new IndexOutOfBoundsException()));
      }
    });
    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10, validColumns);
  }

  @Test
  public void testRowTotalNullInputValidColumns() {
    int[] validColumns = {0};
    final Values2D values = null;

    thrown.expect(InvalidParameterException.class);
    DataUtilities.calculateRowTotal(values, 10, validColumns);
  }

  @Test
  public void testCloneValidInput () {
    final double [][] toClone = {
      {1.1, 2.2}
    };

    double [][] result = DataUtilities.clone(toClone);
    assertArrayEquals("the cloned array should match the original one toClone[0]", result, toClone);
  }

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

     assertArrayEquals(
         "Testing getCumulativePercentages with valid KeyedValues instance",
         expectedValues.toArray(),
         actualValues.toArray());
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

    assertArrayEquals(
        "Testing getCumulativePercentages with valid KeyedValues instance",
        expectedValues.toArray(),
        actualValues.toArray());
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

    final double sum = 1+2+0+3+2;
    final List<Number> expectedValues = Arrays.asList(
        1/sum,
        (1+2)/sum,
        (1+2)/sum,
        (1+2+3)/sum,
        (1+2+3+2)/sum);

    List<Number> actualValues = new ArrayList<Number>();

    for(int i = 0; i < keys.size(); i++) {
      // copy all values into comparable list
      Comparable key = keys.get(i);
      actualValues.add(actual.getValue(key));
    }

    assertArrayEquals(
        "Testing getCumulativePercentages with valid KeyedValues instance",
        expectedValues.toArray(),
        actualValues.toArray());
  } // */

  @Test
  public void testCumulativePercentagesSequentialInvalidItemCount() {
    Mockery mockingContext = new Mockery();

    final List<Comparable> keys = Arrays.asList(0, 1, 2, 3, 4);

    final KeyedValues values = mockingContext.mock(KeyedValues.class);
    mockingContext.checking(new Expectations() {
      {
        allowing(values).getItemCount();
        will(returnValue(10));
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
        will(returnValue(10));
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
}
