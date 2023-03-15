package org.jfree.data;
import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collection;



import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

//@RunWith(Parameterized.class)
public class RangeTest {
	
@Rule
public ExpectedException thrown = ExpectedException.none();

private Range exampleRange1, exampleRange2, exampleRange3, exampleRange4, exampleRange5, exampleRange6, range, range1, range2, range3, range4, range5, range6, baseRange;
private Range range11, range12, range13, range14;
private Range rangea, rangeb, rangec, ranged, rangee, rangef;
private Range inputRange;
private double lowerMargin;
private double upperMargin;
private Range expectedOutputRange;



@BeforeClass public static void setUpBeforeClass() throws Exception {
}
@Before
public void setUp() throws Exception {
	exampleRange1 = new Range(5, 10);
	exampleRange2 = new Range(-2, 3);
	exampleRange3 = null;
	exampleRange4 = new Range(1,7);
	exampleRange5 = new Range(6,9);
	exampleRange6 = new Range (5,10);
	range         = new Range(0, 10);
	range1 = new Range(1, 10);
    range2 = new Range(-5, 5);
    range3 = new Range(0, 0);
    range4 = new Range(1, 1);
    range5 = new Range(0, 10);
    baseRange = new Range(0.0, 10.0);
    
    range11 = new Range(0.0, 10.0);
    range12 = new Range(-5.0, 5.0);
    range13 = new Range(20.0, 30.0);
    range14 = new Range(Double.NaN, Double.NaN);
    
    rangea = new Range(0.0, 10.0);
    rangeb = new Range(-5.0, 5.0);
    rangec = new Range(20.0, 30.0);
    ranged = new Range(Double.NaN, Double.NaN);
    rangee = new Range(Double.NaN, 5.0);
    rangef = new Range(0.0, Double.NaN);
}


/*
* Tests for method toString
* 1 test
*/
@Test
public void testToString() {
String expected = "Range[5.0,10.0]";
String result = exampleRange1.toString();
assertEquals("The new range should be (5, 10)", expected, result);
//assertEquals(expected, result);
}
/*
* Tests for method Combine
* 10 tests
*/
@Test
public void testCombineWithRange1InsideRange2() {
	assertEquals("The new range should be (5, 10)", exampleRange1, Range.combine(exampleRange5, exampleRange1));
}
/* Test combines Range 1 = (5,10)
* 				 Range 2 = (6,9)
* Test produces an error:
* IllegalArgumentException: Range(double,double): require lower (6.0) <= upper (5.0)
*/
@Test
public void testCombineWithRange2InsideRange1() {
	assertEquals("The new range should be (5, 10)", exampleRange1, Range.combine(exampleRange1, exampleRange5));
}
/* Test combines Range 1 = (1,7)
* 				 Range 2 = (5,10)
* Test produces an error:
* IllegalArgumentException: Range(double,double): require lower (5.0) <= upper (1.0)
*/
@Test
public void testCombineWithLowRange1IntersectHighRange2() {
	Range result = new Range(1,10);
	assertEquals("The new range should be (1, 10)", result, Range.combine(exampleRange4, exampleRange1));
}
@Test
public void testCombineWithHighRange1IntersectWithLowRange2() {
	Range result = new Range(1,10);
	assertEquals("The new range should be (1, 10)", result, Range.combine(exampleRange1, exampleRange4));
}
@Test
public void testCombineRange1EqualsRange2() {
	assertEquals("The new range should be (1, 7)", exampleRange4, Range.combine(exampleRange4, exampleRange4));
}
/* Test combines Range 1 = (-2,3)
* 				 Range 2 = (5,10)
* Test produces an error:
* IllegalArgumentException: Range(double,double): require lower (5.0) <= upper (-2.0)
*/
@Test
public void testCombineLowRange1HighRange2() {
	Range result = new Range(-2,10);
	assertEquals("The new range should be (-2, 10)", result, Range.combine(exampleRange2, exampleRange1));
}
@Test
public void testCombineHighRange1LowRange2() {
	Range result = new Range(-2,10);
	assertEquals("The new range should be (-2, 10)", result, Range.combine(exampleRange1, exampleRange2));
}
@Test
public void testCombineWithNullRange1() {
	assertEquals("The new range should be (-2, 3)", exampleRange2, Range.combine(exampleRange3, exampleRange2));
}
@Test
public void testCombineWithNullRange2() {
	assertEquals("The new range should be (5, 10)", exampleRange1, Range.combine(exampleRange1, exampleRange3));
}
@Test
public void testCombineWithBothNullRanges() {
	assertEquals("The new range should be null", null, Range.combine(exampleRange3, exampleRange3));
}
/*
* Tests for method Equals
* 3 tests
*/
@Test
public void testEqualsWithEquivalentObject() {
	Range tempRange = new Range(5,10);
	assertTrue(exampleRange1.equals(tempRange));
}
@Test
public void testEqualsWithNonEquivalentObject() {
	assertFalse(exampleRange2.equals(exampleRange1));
}
@Test
public void testEqualsWithNullObject() {
	assertFalse(exampleRange1.equals(exampleRange3));	
}
@Test
public void testIntersects_Overlap_LowerBound_UpperBound() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(3, 8);

    assertTrue(range1.intersects(3,8));
}

@Test
public void testIntersects_Overlap_LowerBound() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(3, 8);
    System.out.println(range1.intersects(3, 5));
    assertTrue(range1.intersects(3, 5));
}

@Test
public void testIntersects_Overlap_UpperBound() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(3, 8);
    
    
    assertTrue(range1.intersects(0, 3));
}

@Test
public void testIntersects_CompleteOverlap() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(3, 8);

    System.out.println(range1.intersects(0, 5));
    assertTrue(range1.intersects(0, 5));
}

@Test
public void testIntersects_NoOverlap_GreaterThan() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(6, 10);

    assertFalse(range1.intersects(6, 10));
}

@Test
public void testIntersects_NoOverlap_LessThan() {
    Range range1 = new Range(0, 5);
    //Range range2 = new Range(-2, -1);

    assertFalse(range1.intersects(-2, -1));
}



@Test
public void testGetUpperBound() {
    Range range = new Range(0, 5);
    //System.out.println(range.getUpperBound());
    assertEquals(5.0, range.getUpperBound(), 0);
}

//New Tests
///////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//LowerBound

@Test
public void testGetLowerBound() {
    double lowerBound = range.getLowerBound();

    // Test that the returned lower bound is correct
    assertEquals(0.0, lowerBound, 0.01);
}

@Test
public void testGetLowerBoundForNegativeLowerBound() {
    // Create a Range object with lower bound = -10 and upper bound = 10
    range = new Range(-10, 10);

    double lowerBound = range.getLowerBound();

    // Test that the returned lower bound is correct
    assertEquals(-10.0, lowerBound, 0.01);
}

@Test
public void testGetLowerBoundForZeroLowerBound() {
    // Create a Range object with lower bound = 0 and upper bound = 10
    range = new Range(0, 10);

    double lowerBound = range.getLowerBound();

    // Test that the returned lower bound is correct
    assertEquals(0.0, lowerBound, 0.01);
}

@Test
public void testGetLowerBoundForUpperBoundEqualToLowerBound() {
    // Create a Range object with lower bound = 10 and upper bound = 10
    range = new Range(10, 10);

    double lowerBound = range.getLowerBound();

    // Test that the returned lower bound is correct
    assertEquals(10.0, lowerBound, 0.01);
}

@Test
public void testGetLowerBoundForPositiveLowerBound() {
    // Create a Range object with lower bound = 5 and upper bound = 10
    range = new Range(5, 10);

    double lowerBound = range.getLowerBound();

    // Test that the returned lower bound is correct
    assertEquals(5.0, lowerBound, 0.01);
}



@Test
public void testGetLowerBound2() {
    // Equivalence class: normal input
    Range range = new Range(2, 6);
    double expectedLowerBound = 2;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}

@Test
public void testGetLowerBoundWithNegativeRange() {
    // Equivalence class: negative range input
    Range range = new Range(-6, -2);
    double expectedLowerBound = -6;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}

@Test
public void testGetLowerBoundWithZeroRange() {
    // Equivalence class: zero range input
    Range range = new Range(0, 0);
    double expectedLowerBound = 0;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}

@Test
public void testGetLowerBoundWithInfinityRange() {
    // Equivalence class: infinity range input
    Range range = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    double expectedLowerBound = Double.NEGATIVE_INFINITY;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}

@Test
public void testGetLowerBoundWithNaNRange() {
    // Equivalence class: NaN range input
    Range range = new Range(Double.NaN, Double.NaN);
    double expectedLowerBound = Double.NaN;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}

@Test
public void testGetLowerBoundWithLargeRange() {
    // Equivalence class: large range input
    Range range = new Range(1E15, 1E16);
    double expectedLowerBound = 1E15;
    double actualLowerBound = range.getLowerBound();
    assertEquals(expectedLowerBound, actualLowerBound, 0.0);
}




//GetLength///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testGetLength() {
    double length = range.getLength();

    // Test that the returned length is correct
    assertEquals(10.0, length, 0.01);
}

@Test
public void testGetLengthForNegativeRange() {
    // Create a Range object with lower bound = -10 and upper bound = -5
    range = new Range(-10, -5);

    double length = range.getLength();

    // Test that the returned length is correct
    assertEquals(5.0, length, 0.01);
}

@Test
public void testGetLengthForZeroRange() {
    // Create a Range object with lower bound = 0 and upper bound = 0
    range = new Range(0, 0);

    double length = range.getLength();

    // Test that the returned length is correct
    assertEquals(0.0, length, 0.01);
}

@Test
public void testGetLengthForUpperBoundEqualToLowerBound() {
    // Create a Range object with lower bound = 10 and upper bound = 10
    range = new Range(10, 10);

    double length = range.getLength();

    // Test that the returned length is correct
    assertEquals(0.0, length, 0.01);
}

@Test
public void testGetLengthForPositiveRange() {
    // Create a Range object with lower bound = 5 and upper bound = 10
    range = new Range(5, 10);

    double length = range.getLength();

    // Test that the returned length is correct
    assertEquals(5.0, length, 0.01);
}


//Range Constructor////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Test for a valid range with lower bound < upper bound
@Test
public void testValidRangeWithLowerBoundLessThanUpperBound() {
    Range range = new Range(0, 10);
    assertNotNull(range);
}

// Test for a valid range with lower bound = upper bound
@Test
public void testValidRangeWithLowerBoundEqualsUpperBound() {
    Range range = new Range(10, 10);
    assertNotNull(range);
}

// Test for a valid range with negative values
@Test
public void testValidRangeWithNegativeValues() {
    Range range = new Range(-10, -5);
    assertNotNull(range);
}

// Test for a valid range with zero values
@Test
public void testValidRangeWithZeroValues() {
    Range range = new Range(0, 0);
    assertNotNull(range);
}

// Test for an invalid range with lower bound > upper bound
@Test
public void testInvalidRangeWithLowerBoundGreaterThanUpperBound() {
    thrown.expect(IllegalArgumentException.class);
    Range range = new Range(5, 0);
}

// Test for an invalid range with lower bound = NaN
@Test
public void testInvalidRangeWithLowerBoundAsNaN() {
    //thrown.expect(IllegalArgumentException.class);
	assertNotNull(new Range(Double.NaN, 10));
}

// Test for an invalid range with upper bound = NaN
@Test
public void testValidRangeWithUpperBoundAsNaN() {
    //thrown.expect(IllegalArgumentException.class);
    assertNotNull(new Range(0, Double.NaN));
}



// Test for an invalid range with upper bound = POSITIVE_INFINITY
@Test
public void testValidRangeWithUpperBoundAsPositiveInfinity() {
    //thrown.expect(IllegalArgumentException.class);
	assertNotNull(new Range(0, Double.POSITIVE_INFINITY));
}

// Test for an invalid range with lower bound = NEGATIVE_INFINITY
@Test
public void testInvalidRangeWithLowerBoundAsNegativeInfinity() {
    //thrown.expect(IllegalArgumentException.class);
	assertNotNull(new Range(Double.NEGATIVE_INFINITY, 10));
}


//Get Central Value//////////////////////////////////////////////////////////////////////////////////////////////////////////
@Test
public void testGetCentralValueRange1() {
    // Test range [1, 10]
    double expected = 5.5;
    double actual = range1.getCentralValue();
    assertEquals(expected, actual, 0.01); // Equivalent class: non-empty range with odd number of elements
}

@Test
public void testGetCentralValueRange2() {
    // Test range [-5, 5]
    double expected = 0;
    double actual = range2.getCentralValue();
    assertEquals(expected, actual, 0.01); // Equivalent class: non-empty range with even number of elements
}

@Test
public void testGetCentralValueRange3() {
    // Test range [0, 0]
    double expected = 0;
    double actual = range3.getCentralValue();
    assertEquals(expected, actual, 0.01); // Equivalent class: single element range
}


//contains//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testContainsInRange1() {
    // Test range [1, 10]
    double value = 5;
    assertTrue(range1.contains(value)); // Equivalent class: value within range
}

@Test
public void testContainsOutOfRange1() {
    // Test range [1, 10]
    double value = 11;
    assertFalse(range1.contains(value)); // Equivalent class: value outside range
}

@Test
public void testContainsInRange2() {
    // Test range [-5, 5]
    double value = 0;
    assertTrue(range2.contains(value)); // Equivalent class: value within range
}

@Test
public void testContainsOutOfRange2() {
    // Test range [-5, 5]
    double value = 6;
    assertFalse(range2.contains(value)); // Equivalent class: value outside range
}

@Test
public void testContainsSingleElementRange() {
    // Test range [0, 0]
    double value = 0;
    assertTrue(range3.contains(value)); // Equivalent class: value within single element range
}

@Test
public void testContainsValueBelowRange() {
    // Test range [1, 10]
    double value = 0;
    assertFalse(range1.contains(value)); // Equivalent class: value below range
}

@Test
public void testContainsValueAboveRange() {
    // Test range [1, 10]
    double value = 12;
    assertFalse(range1.contains(value)); // Equivalent class: value above range
}

@Test
public void testContainsWithinRange() {
    assertTrue(range1.contains(5.0));
}

// Test contains value outside the range
@Test
public void testContainsOutsideRange() {
    assertFalse(range1.contains(20.0));
}

// Test contains value on the lower bound
@Test
public void testContainsOnLowerBound() {
    assertTrue(range1.contains(5.0));
}

// Test contains value on the upper bound
@Test
public void testContainsOnUpperBound() {
    assertTrue(range1.contains(10.0));
}

// Test contains value on NaN range
@Test
public void testContainsOnNanRange() {
    assertFalse(range4.contains(Double.NaN));
}

// Test contains value on lower bound of NaN range
@Test
public void testContainsOnLowerBoundOfNanRange() {
    assertFalse(range4.contains(Double.NEGATIVE_INFINITY));
}

// Test contains value on upper bound of NaN range
@Test
public void testContainsOnUpperBoundOfNanRange() {
    assertFalse(range4.contains(Double.POSITIVE_INFINITY));
}

//constrain/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testConstrainInRange1() {
    // Test range [1, 10]
    double value = 5;
    assertEquals(value, range1.constrain(value), 0.001); // Equivalent class: value within range
}

@Test
public void testConstrainOutOfRange1Lower() {
    // Test range [1, 10]
    double value = 0;
    assertEquals(1, range1.constrain(value), 0.001); // Equivalent class: value below range
}

@Test
public void testConstrainOutOfRange1Upper() {
    // Test range [1, 10]
    double value = 11;
    assertEquals(10, range1.constrain(value), 0.001); // Equivalent class: value above range
}

@Test
public void testConstrainInRange2() {
    // Test range [-5, 5]
    double value = -3;
    assertEquals(value, range2.constrain(value), 0.001); // Equivalent class: value within range
}

@Test
public void testConstrainOutOfRange2Lower() {
    // Test range [-5, 5]
    double value = -10;
    assertEquals(-5, range2.constrain(value), 0.001); // Equivalent class: value below range
}

@Test
public void testConstrainOutOfRange2Upper() {
    // Test range [-5, 5]
    double value = 10;
    assertEquals(5, range2.constrain(value), 0.001); // Equivalent class: value above range
}

@Test
public void testConstrainInRange3() {
    // Test range [0, 0]
    double value = 0;
    assertEquals(value, range3.constrain(value), 0.001); // Equivalent class: single value range
}

@Test
public void testConstrainOutOfRange3() {
    // Test range [0, 0]
    double value = 5;
    assertEquals(0, range3.constrain(value), 0.001); // Equivalent class: value outside range (always return the central value)
}


//expandToInclude/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testExpandToInclude1() {
    // Test expanding range [1, 10] to include value 5
    Range expandedRange = Range.expandToInclude(range1, 5);
    assertEquals(1, expandedRange.getLowerBound(), 0.001); // Equivalent class: range with non-zero lower bound
    assertEquals(10, expandedRange.getUpperBound(), 0.001); // Equivalent class: range with non-zero upper bound
}

@Test
public void testExpandToInclude2() {
    // Test expanding range [-5, 5] to include value 10
    Range expandedRange = Range.expandToInclude(range2, 10);
    assertEquals(-5, expandedRange.getLowerBound(), 0.001); // Equivalent class: range with negative lower bound
    assertEquals(10, expandedRange.getUpperBound(), 0.001); // Equivalent class: value is outside the original range
}

@Test
public void testExpandToInclude3() {
    // Test expanding range [0, 0] to include value 0
    Range expandedRange = Range.expandToInclude(range3, 0);
    assertEquals(0, expandedRange.getLowerBound(), 0.001); // Equivalent class: single value range
    assertEquals(0, expandedRange.getUpperBound(), 0.001);
}

@Test
public void testExpandToInclude4() {
    // Test expanding range [1, 1] to include value 0
    Range expandedRange = Range.expandToInclude(range4, 0);
    assertEquals(0, expandedRange.getLowerBound(), 0.001); // Equivalent class: value is lower than the original lower bound
    assertEquals(1, expandedRange.getUpperBound(), 0.001);
}

@Test
public void testExpandToInclude5() {
    // Test expanding null range to include value 5
    Range expandedRange = Range.expandToInclude(range6, 5);
    assertEquals(5, expandedRange.getLowerBound(), 0.001); // Equivalent class: null range
    assertEquals(5, expandedRange.getUpperBound(), 0.001);
}

@Test
public void testExpandToInclude6() {
    // Test exception for null input range
    assertNotNull(Range.expandToInclude(null, 5));
}


//hashcode////////////////////////////////////////////////////////////////////////////////

@Test
public void testHashCode1() {
    // Test hash code for non-null range
    int hashCode = range1.hashCode();
    assertNotNull(hashCode);
}


//equals//////////////////////////////////////////////////////////////////////////////////	/////////////////////////
@Test
public void testEquals1() {
    // Test equality between range [1, 10] and itself
    assertTrue(range1.equals(range1)); // Equivalent class: same range object
}

@Test
public void testEquals2() {
    // Test equality between range [1, 10] and range [1, 10]
    Range range = new Range(1, 10);
    assertTrue(range1.equals(range)); // Equivalent class: same range values
}

@Test
public void testEquals3() {
    // Test equality between range [1, 10] and range [1, 5]
    Range range = new Range(1, 5);
    assertFalse(range1.equals(range)); // Equivalent class: different upper bound
}

@Test
public void testEquals4() {
    // Test equality between range [-5, 5] and range [-5, 5]
    Range range = new Range(-5, 5);
    assertTrue(range2.equals(range)); // Equivalent class: same range values
}

@Test
public void testEquals5() {
    // Test equality between range [0, 0] and range [0, 0]
    Range range = new Range(0, 0);
    assertTrue(range3.equals(range)); // Equivalent class: same range values
}

@Test
public void testEquals6() {
    // Test equality between range [1, 1] and range [1, 1]
    Range range = new Range(1, 1);
    assertTrue(range4.equals(range)); // Equivalent class: same range values
}

@Test
public void testEquals7() {
    // Test equality between range [0, 10] and range [0, 10]
    Range range = new Range(0, 10);
    assertTrue(range5.equals(range)); // Equivalent class: same range values
}

@Test
public void testEquals8() {
    // Test equality between range [0, 10] and range [0, 5]
    Range range = new Range(0, 5);
    assertFalse(range5.equals(range)); // Equivalent class: different upper bound
}

@Test
public void testEquals9() {
    // Test equality between range [1, 10] and null
    assertFalse(range1.equals(null)); // Equivalent class: comparing to null object
}

@Test
public void testEquals10() {
    // Test equality between null and range [1, 10]
    assertFalse(range1.equals(range6)); // Equivalent class: comparing to null object
}

//shift1//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//@Test(expected = InvalidParameterException.class) // EC1
@Test (expected = IllegalArgumentException.class)
public void testShift_NullBase() {
    Range base = null;
    double delta = 1.0;
    boolean allowZeroCrossing = true;
    Range.shift(base, delta, allowZeroCrossing);
}

@Test // EC2
public void testShift_ZeroDelta() {
    Range base = new Range(-1, 1);
    double delta = 0;
    boolean allowZeroCrossing = true;
    Range expected = new Range(-1, 1);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC4
public void testShift_PositiveDelta_AllowCrossing() {
    Range base = new Range(-1, 1);
    double delta = 0.5;
    boolean allowZeroCrossing = true;
    Range expected = new Range(-0.5, 1.5);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_NoCrossing() {
    Range base = new Range(-1, 1);
    double delta = 1.5;
    boolean allowZeroCrossing = false;
    Range expected = new Range(0, 2.5);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_Crossing() {
    Range base = new Range(-1, 1);
    double delta = 1.5;
    boolean allowZeroCrossing = true;
    Range expected = new Range(0.5, 2.5);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_CrossingNegativeToPositive() {
    Range base = new Range(-2, -1);
    double delta = 1.5;
    boolean allowZeroCrossing = true;
    Range expected = new Range(-0.5, 0.5);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_CrossingPositiveToNegative() {
    Range base = new Range(1, 2);
    double delta = 1.5;
    boolean allowZeroCrossing = true;
    Range expected = new Range(2.5, 3.5);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}


@Test // EC3, EC5
public void testShift_PositiveDelta_MaxValue_NoCrossing() {
    Range base = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
    double delta = 1.0;
    boolean allowZeroCrossing = false;
    Range expected = new Range(-Double.MAX_VALUE + 1, Double.MAX_VALUE);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC4
public void testShift_PositiveDelta_MinValue_AllowCrossing() {
    Range base = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
    double delta = 1.0;
    boolean allowZeroCrossing = true;
    Range expected = new Range(Double.MIN_VALUE + 1, Double.MAX_VALUE + 1);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_MinValue_NoCrossing() {
    Range base = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
    double delta = 1.0;
    boolean allowZeroCrossing = false;
    Range expected = new Range(Double.MIN_VALUE + 1, Double.MAX_VALUE);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC4
public void testShift_PositiveDelta_LargeRange_AllowCrossing() {
    Range base = new Range(-1e20, 1e20);
    double delta = 1e19;
    boolean allowZeroCrossing = true;
    Range expected = new Range(-9e19, 11e19);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}

@Test // EC3, EC5
public void testShift_PositiveDelta_LargeRange_NoCrossing() {
    Range base = new Range(-1e20, 1e20);
    double delta = 3e20;
    boolean allowZeroCrossing = false;
    Range expected = new Range(0, 1e20 + 3e20);
    Range actual = Range.shift(base, delta, allowZeroCrossing);
    assertEquals(expected, actual);
}


//shift2///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Test
public void testShift() {
    // test for regular values
    Range shiftedRange = Range.shift(baseRange, 5.0);
    assertEquals("Shifted range is not correct", new Range(5.0, 15.0), shiftedRange);

    // test for delta equal to 0
    Range zeroShiftedRange = Range.shift(baseRange, 0.0);
    assertEquals("Shifted range is not correct", baseRange, zeroShiftedRange);

    // test for negative delta value
    Range negativeShiftedRange = Range.shift(baseRange, -5.0);
    assertEquals("Shifted range is not correct", new Range(-5.0, 5.0), negativeShiftedRange);
}

// Equivalence classes:
// 1. Base range is null
// 2. Base range is not null
// 3. Delta value is positive
// 4. Delta value is zero
// 5. Delta value is negative

// Test cases for all possible combinations of equivalence classes:


@Test
public void testShift_PositiveDelta() {
    Range shiftedRange = Range.shift(baseRange, 5.0);
    assertEquals("Shifted range is not correct", new Range(5.0, 15.0), shiftedRange);
}

@Test
public void testShift_ZeroDelta1() {
    Range shiftedRange = Range.shift(baseRange, 0.0);
    assertEquals("Shifted range is not correct", baseRange, shiftedRange);
}

@Test
public void testShift_NegativeDelta() {
    Range shiftedRange = Range.shift(baseRange, -5.0);
    assertEquals("Shifted range is not correct", new Range(-5.0, 5.0), shiftedRange);
}


//expand//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
(expected = IllegalArgumentException.class)
public void testExpandWithNullRange() {
     Range.expand(null, 0.25, 0.5);
        
}

@Test
public void testExpandWithZeroMargins() {
    Range inputRange = new Range(2, 6);
    double lowerMargin = 0;
    double upperMargin = 0;
    Range expectedOutputRange = new Range(2, 6);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithPositiveMargins() {
    // Equivalence class: positive margins
    Range inputRange = new Range(-2, 6);
    double lowerMargin = 0.25;
    double upperMargin = 0.5;
    Range expectedOutputRange = new Range(-4, 10);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithNegativeMargins() {
    // Equivalence class: negative margins
    Range inputRange = new Range(-2, 6);
    double lowerMargin = -0.25;
    double upperMargin = -0.5;
    Range expectedOutputRange = new Range(0, 2);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithMixedMargins() {
    // Equivalence class: mixed margins
    Range inputRange = new Range(-2, 6);
    double lowerMargin = -0.25;
    double upperMargin = 0.5;
    Range expectedOutputRange = new Range(0, 10);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithMarginsEqualToRangeLength() {
    // Equivalence class: margins equal to range length
    Range inputRange = new Range(-2, 6);
    double lowerMargin = 0.4;
    double upperMargin = 0.4;
    Range expectedOutputRange = new Range(-5.2, 9.2);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithMarginsGreaterThanRangeLength() {
    // Equivalence class: margins greater than range length
    Range inputRange = new Range(-2, 6);
    double lowerMargin = 0.8;
    double upperMargin = 0.8;
    Range expectedOutputRange = new Range(-8.4, 12.4);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithMarginsEqualToNegativeRangeLength() {
    // Equivalence class: margins equal to negative range length
    Range inputRange = new Range(-2, 6);
    double lowerMargin = 0.6;
    double upperMargin = 0.6;
    Range expectedOutputRange = new Range(-6.8, 10.8);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}

@Test
public void testExpandWithMarginsLessThanNegativeRangeLength() {
    // Equivalence class: margins less than negative range length
    Range inputRange = new Range(-5, -2);
    double lowerMargin = -0.6;
    double upperMargin = -0.6;
    Range expectedOutputRange = new Range(-3.5, -3.5);
    Range actualOutputRange = Range.expand(inputRange, lowerMargin, upperMargin);
    assertEquals(expectedOutputRange, actualOutputRange);
}


//isnanrange////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testIsNaNRangeWithNaNRange() {
    // Equivalence class: range with NaN bounds
    Range range = new Range(Double.NaN, Double.NaN);
    boolean expectedIsNaNRange = true;
    boolean actualIsNaNRange = range.isNaNRange();
    assertEquals(expectedIsNaNRange, actualIsNaNRange);
}

@Test
public void testIsNaNRangeWithSingleNaNBound() {
    // Equivalence class: range with one NaN bound
    Range range1 = new Range(Double.NaN, 10);
    Range range2 = new Range(10, Double.NaN);
    Range range3 = new Range(Double.NaN, Double.POSITIVE_INFINITY);
    boolean expectedIsNaNRange = false;
    boolean actualIsNaNRange1 = range1.isNaNRange();
    boolean actualIsNaNRange2 = range2.isNaNRange();
    boolean actualIsNaNRange3 = range3.isNaNRange();
    assertEquals(expectedIsNaNRange, actualIsNaNRange1);
    assertEquals(expectedIsNaNRange, actualIsNaNRange2);
    assertEquals(expectedIsNaNRange, actualIsNaNRange3);
}

@Test
public void testIsNaNRangeWithNormalRange() {
    // Equivalence class: normal range with finite bounds
    Range range = new Range(2, 6);
    boolean expectedIsNaNRange = false;
    boolean actualIsNaNRange = range.isNaNRange();
    assertEquals(expectedIsNaNRange, actualIsNaNRange);
}

@Test
public void testIsNaNRangeWithInfinityRange() {
    // Equivalence class: range with infinite bounds
    Range range = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    boolean expectedIsNaNRange = false;
    boolean actualIsNaNRange = range.isNaNRange();
    assertEquals(expectedIsNaNRange, actualIsNaNRange);
}


//scale///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testScaleWithPositiveFactor() {
    // Equivalence class: base range with finite bounds and positive scaling factor
    Range base = new Range(2, 6);
    double factor = 1.5;
    Range expectedScaledRange = new Range(3, 9);
    Range actualScaledRange = Range.scale(base, factor);
    assertEquals(expectedScaledRange, actualScaledRange);
}

@Test
public void testScaleWithZeroFactor() {
    // Equivalence class: base range with finite bounds and zero scaling factor
    Range base = new Range(2, 6);
    double factor = 0;
    Range expectedScaledRange = new Range(0, 0);
    Range actualScaledRange = Range.scale(base, factor);
    assertEquals(expectedScaledRange, actualScaledRange);
}

@Test(expected = IllegalArgumentException.class)
public void testScaleWithNegativeFactor() {
    // Equivalence class: negative scaling factor
    Range base = new Range(2, 6);
    double factor = -1;
    Range actualScaledRange = Range.scale(base, factor);
}

@Test
public void testScaleWithInfiniteBaseRange() {
    // Equivalence class: base range with infinite bounds
    Range base1 = new Range(Double.NEGATIVE_INFINITY, 5);
    Range base2 = new Range(5, Double.POSITIVE_INFINITY);
    double factor = 2;
    Range expectedScaledRange1 = new Range(Double.NEGATIVE_INFINITY, 10);
    Range expectedScaledRange2 = new Range(10, Double.POSITIVE_INFINITY);
    Range actualScaledRange1 = Range.scale(base1, factor);
    Range actualScaledRange2 = Range.scale(base2, factor);
    assertEquals(expectedScaledRange1, actualScaledRange1);
    assertEquals(expectedScaledRange2, actualScaledRange2);
}

@Test
public void testScaleWithNaNBaseRange() {
    // Equivalence class: base range with NaN bounds
    Range base = new Range(Double.NaN, Double.NaN);
    double factor = 2;
    Range actualScaledRange = Range.scale(base, factor);
    assertTrue(actualScaledRange.isNaNRange());
}

@Test
public void testScaleWithFactorGreaterThanOne() {
    // Equivalence class: scaling factor greater than 1
    Range base = new Range(2, 6);
    double factor = 2;
    Range expectedScaledRange = new Range(4, 12);
    Range actualScaledRange = Range.scale(base, factor);
    assertEquals(expectedScaledRange, actualScaledRange);
}

@Test
public void testScaleWithFactorLessThanOne() {
    // Equivalence class: scaling factor less than 1
    Range base = new Range(2, 6);
    double factor = 0.5;
    Range expectedScaledRange = new Range(1, 3);
    Range actualScaledRange = Range.scale(base, factor);
    assertEquals(expectedScaledRange, actualScaledRange);
}


//combineIgnoringNaN/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testCombineIgnoringNaNWithBothNull() {
  assertNull(Range.combineIgnoringNaN(null, null));
}

@Test
public void testCombineIgnoringNaNWithFirstNull() {
  Range range2 = new Range(0, 1);
  assertEquals(range2, Range.combineIgnoringNaN(null, range2));
}

@Test
public void testCombineIgnoringNaNWithSecondNull() {
  Range range1 = new Range(0, 1);
  assertEquals(range1, Range.combineIgnoringNaN(range1, null));
}

@Test
public void testCombineIgnoringNaNWithBothRangesNaN() {
  Range range1 = new Range(Double.NaN, Double.NaN);
  Range range2 = new Range(Double.NaN, Double.NaN);
  assertNull(Range.combineIgnoringNaN(range1, range2));
}

@Test
public void testCombineIgnoringNaNWithFirstRangeNaN() {
  Range range1 = new Range(Double.NaN, Double.NaN);
  Range range2 = new Range(0, 1);
  assertEquals(range2, Range.combineIgnoringNaN(range1, range2));
}

@Test
public void testCombineIgnoringNaNWithSecondRangeNaN() {
  Range range1 = new Range(0, 1);
  Range range2 = new Range(Double.NaN, Double.NaN);
  assertEquals(range1, Range.combineIgnoringNaN(range1, range2));
}

@Test
public void testCombineIgnoringNaNWithBothRangesValid() {
  Range range1 = new Range(0, 1);
  Range range2 = new Range(2, 3);
  Range expectedRange = new Range(0, 3);
  assertEquals(expectedRange, Range.combineIgnoringNaN(range1, range2));
}

@Test
public void testCombineIgnoringNaNWithOverlappingRanges() {
  Range range1 = new Range(0, 2);
  Range range2 = new Range(1, 3);
  Range expectedRange = new Range(0, 3);
  assertEquals(expectedRange, Range.combineIgnoringNaN(range1, range2));
}

@Test
public void testCombineIgnoringNaNWithAdjacentRanges() {
  Range range1 = new Range(0, 1);
  Range range2 = new Range(1, 2);
  Range expectedRange = new Range(0, 2);
  assertEquals(expectedRange, Range.combineIgnoringNaN(range1, range2));
}

//Test combine two normal ranges
@Test
public void testCombineIgnoringNaNNormal() {
    Range result = Range.combineIgnoringNaN(range1, range2);
    assertEquals(-5.0, result.getLowerBound(), 0.0001);
    assertEquals(10.0, result.getUpperBound(), 0.0001);
}

// Test combine two ranges where one range is null
@Test
public void testCombineIgnoringNaNOneNull() {
    Range result = Range.combineIgnoringNaN(range1, null);
    assertSame(range1, result);
}

// Test combine two NaN ranges
@Test
public void testCombineIgnoringNaNNaN() {
    Range result = Range.combineIgnoringNaN(range4, range4);
    assertNotNull(result);
}

// Test combine two ranges where one range is NaN
@Test
public void testCombineIgnoringNaNNanOneNormal() {
    Range result1 = Range.combineIgnoringNaN(range1, range5);
    assertNotNull(result1);
    Range result2 = Range.combineIgnoringNaN(range6, range2);
    assertNotNull(result2);
}

// Test combine two ranges where both ranges have one NaN bound
@Test
public void testCombineIgnoringNaNOneNanBound() {
    Range result = Range.combineIgnoringNaN(range5, range6);
    assertNotNull(result);
}

// Test combine two ranges where both ranges have different NaN bounds
@Test
public void testCombineIgnoringNaNDifferentNanBounds() {
    Range result = Range.combineIgnoringNaN(range5, range6);
    assertSame(result, range5);
}


//intersects//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Test
public void testIntersectsWhenRangeInsideGivenRange() {
    Range r1 = new Range(2, 5);
    assertTrue(r1.intersects(1, 8));
}

@Test
public void testIntersectsWhenGivenRangeInsideRange() {
    Range r1 = new Range(2, 8);
    assertTrue(r1.intersects(4, 6));
}

@Test
public void testIntersectsWhenRangesOverlap() {
    Range r1 = new Range(2, 6);
    assertTrue(r1.intersects(4, 8));
}


@Test
public void testIntersectsWhenRangesDoNotOverlap() {
    Range r1 = new Range(2, 4);
    assertFalse(r1.intersects(5, 8));
}

@Test
public void testIntersectsWhenGivenRangeContainsLowerBound() {
    Range r1 = new Range(2, 6);
    assertTrue(r1.intersects(2, 4));
}

@Test
public void testIntersectsWhenGivenRangeContainsUpperBound() {
    Range r1 = new Range(2, 6);
    assertTrue(r1.intersects(4, 6));
}

@Test
public void testIntersectsWhenGivenRangeIsEqualToRange() {
    Range r1 = new Range(2, 6);
    assertTrue(r1.intersects(2, 6));
}



@Test
public void testIntersectsWhenLowerBoundIsGreaterThanUpperBound() {
    Range r1 = new Range(2, 6);
    assertFalse(r1.intersects(8, 4));
}



//intersects2//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Test the intersection of two ranges with normal values
@Test
public void testIntersectsNormal() {
    assertTrue(range1.intersects(range2));
}

// Test the intersection of two ranges with no intersection
@Test
public void testIntersectsNoIntersection() {
    assertFalse(range1.intersects(range3));
}

// Test the intersection of two ranges where one range is a NaN range
@Test
public void testIntersectsOneNanRange() {
    assertTrue(range11.intersects(range4));
}

// Test the intersection of two ranges where one range is a NaN range and the other is a normal range
@Test
public void testIntersectsOneNanOneNormalRange() {
    assertTrue(range4.intersects(range2));
}


@Test
public void shiftTestYesCrossZeroBase() {
    
    // created expected range after shift
    Range expected = new Range(7.0, 12.0);
    // Call shift on example range
    Range shiftedExample1 = Range.shift(exampleRange1, 2.0, true);
    
    // Check if expected equals returned shifted range
    boolean flag = false;
    if(shiftedExample1.equals(expected)) {
        flag = true;
    }
    assertTrue("The shift was not as expected", flag);
}

@Test
public void shiftTestNoCrossZeroBounds() {
    
    // created expected range after shift
    Range zeroBoundsExample = new Range(0.0, .0);
    Range expected = new Range(2.0, 2.0);
    // Call shift on example range
    Range shiftedExample1 = Range.shift(zeroBoundsExample, 2.0, false);
    
    // Check if expected equals returned shifted range
    boolean flag = false;
    if(shiftedExample1.equals(expected)) {
        flag = true;
    }
    assertTrue("The shift was not as expected", flag);
}


@Test
public void shiftTestNoCrossPositiveBounds() {
    
    // created expected range after shift
    Range expected = new Range(10.0, 15.0);
    // Call shift on example range
    Range shiftedExample1 = Range.shift(exampleRange1, 5.0, false);
    
    // Check if expected equals returned shifted range
    boolean flag = false;
    if(shiftedExample1.equals(expected)) {
        flag = true;
    }
    
    assertTrue("The shift was not as expected", flag);
}



@After
public void tearDown() throws Exception {
}
@AfterClass
public static void tearDownAfterClass() throws Exception {
}
}
