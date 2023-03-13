package org.jfree.data;

import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import java.security.InvalidParameterException;

public class DataUtilitiesCalculateRowTotalTestUpdated {
  @Test
  public void testRowTotalValidInputs() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(4.1));
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
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(null));
      }
    });

    double result = DataUtilities.calculateRowTotal(values, 0);
    assertEquals("Checking getRowTotal with valid Values2D and column values",
        3.4,
        result,
        .000000001d
    );
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testRowTotalInvalidValues2D() {
    Mockery mockingContext = new Mockery();

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(null));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
      }
    });
    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10);
  }

  /* Testing the overridden method */
  @Test
  public void testRowTotalValidInputsValidColumns() {
    Mockery mockingContext = new Mockery();
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(4.1));
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
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(2));
        one(values).getValue(0, 0);
        will(returnValue(3.4));
        one(values).getValue(0, 1);
        will(returnValue(null));
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
    int[] validColumns = {0};

    final Values2D values = mockingContext.mock(Values2D.class);
    mockingContext.checking(new Expectations() {
      {
        one(values).getColumnCount();
        will(returnValue(10));
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(null));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
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
        one(values).getValue(0,0);
        will(returnValue(1.1));
        one(values).getValue(0,1);
        will(returnValue(2.2));
      }
    });
    thrown.expect(InvalidParameterException.class);

    DataUtilities.calculateRowTotal(values, 10, validColumns);
  }
}

