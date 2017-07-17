/*
 This file is part of ELKI:
 Environment for Developing KDD-Applications Supported by Index-Structures

 Copyright (C) 2017
 ELKI Development Team

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.lmu.ifi.dbs.elki.math.linearalgebra;

import static de.lmu.ifi.dbs.elki.math.linearalgebra.VMath.*;
import static de.lmu.ifi.dbs.elki.math.linearalgebra.VMathOperationsTest.assertDimensionMismatch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Test the VMath class methods operating on matrixs.
 *
 * @author Merlin Dietrich
 * 
 */
public class VMathMatrixTest {
  /**
   * Error message (in assertions!) when matrix dimensionalities do not agree.
   */
  private static final String ERR_MATRIX_DIMENSIONS = "Matrix dimensions do not agree.";

  /**
   * Error message (in assertions!) when matrix dimensionalities do not agree.
   */
  private static final String ERR_MATRIX_INNERDIM = "Matrix inner dimensions do not agree.";
  
  /**
   * Nonsymmetric 4 x 5 (rows x columns) TESTMATRIX
   */
  protected static final double[][] TESTMATRIX = {
      {1,2,3,4,5}, // 
      {2,3,4,5,1}, // 
      {3,4,5,1,2}, // 
      {4,5,1,2,3}, //
  };
  
  protected static final double EPSILON = 1E-15;
  
  /**
   * Testing the transposed(Matrix) method of VMath class.
   */
  @Test
  public void testTransposed() {
    final double[][] m1 = TESTMATRIX;
 
    final double[][] res_case1 = {
          {m1[0][0], m1[1][0], m1[2][0], m1[3][0]}, //
          {m1[0][1], m1[1][1], m1[2][1], m1[3][1]}, //
          {m1[0][2], m1[1][2], m1[2][2], m1[3][2]}, //
          {m1[0][3], m1[1][3], m1[2][3], m1[3][3]}, //
          {m1[0][4], m1[1][4], m1[2][4], m1[3][4]}, // 
    };
    
    assertThat(transpose(m1), is(equalTo(res_case1)));
  }
  
  /**
   * Testing the Matrix plus operations of VMath class.
   */
  @Test
  public void testPlus() {
    // TODO: better Data    
    final double[][] m1                 = {
	  {-14,  1, 2, 0.100000000000006100004}, //
	  {-14,  1, 2, 0.100000000000006100004}, //
	  {-14,  1, 2, 0.100000000000006100004}, //
    };

    final double[][] m2                 = {
	  {  7,  6, 2,-0.1000000000000069}, //
	  {  7,  6, 2,-0.1000000000000069}, //
	  {  7,  6, 2,-0.1000000000000069}, //
    };
    
    final double s = 13;
                
    final double[][] res_plus_I           = {
	    {m1[0][0]+m2[0][0], m1[0][1]+m2[0][1], m1[0][2]+m2[0][2], m1[0][3]+m2[0][3]}, //
	    {m1[1][0]+m2[1][0], m1[1][1]+m2[1][1], m1[1][2]+m2[1][2], m1[1][3]+m2[1][3]}, //
	    {m1[2][0]+m2[2][0], m1[2][1]+m2[2][1], m1[2][2]+m2[2][2], m1[2][3]+m2[2][3]}, //
    };
    final double[][] res_plusTimes_I      = {
	    {m1[0][0]+s*m2[0][0], m1[0][1]+s*m2[0][1], m1[0][2]+s*m2[0][2], m1[0][3]+s*m2[0][3]}, //
	    {m1[1][0]+s*m2[1][0], m1[1][1]+s*m2[1][1], m1[1][2]+s*m2[1][2], m1[1][3]+s*m2[1][3]}, //
	    {m1[2][0]+s*m2[2][0], m1[2][1]+s*m2[2][1], m1[2][2]+s*m2[2][2], m1[2][3]+s*m2[2][3]}, //
    };
    
    // plus  and plusEquals (Matrix + Matrix) 
    assertTrue(almostEquals(res_plus_I, plus(m1,m2), EPSILON));
    assertTrue(almostEquals(res_plus_I, plusEquals(copy(m1), m2), EPSILON));

    // plusTimes() and plusTimesEquals()
    assertTrue(almostEquals(res_plusTimes_I, plusTimes(m1, m2, s), EPSILON));
    assertTrue(almostEquals(res_plusTimes_I, plusTimesEquals(copy(m1), m2, s), EPSILON));
  }
  
  /**
   * Testing the Matrix minus operations of VMath class.
   */
  @Test
  public void testMinus() {
    // TODO: better Data
    final double[][] m1 = {
        {-14,  1, 2}, //
        {-14,  1, 2}, //
        {-14,  1, 2}, //
        {-14,  1, 2}, //
        {-14,  1, 2}, //
    };

    final double[][] m2 = {
        {  7,  6, 2}, //
        {  7,  6, 2}, //
        {  7,  6, 2}, //
        {  7,  6, 2}, //
        {  7,  6, 2}, //
    };
    
    final double s = 13;
                
    final double[][] res_minus_I           = {
	    {m1[0][0]-m2[0][0], m1[0][1]-m2[0][1], m1[0][2]-m2[0][2]}, //
	    {m1[1][0]-m2[1][0], m1[1][1]-m2[1][1], m1[1][2]-m2[1][2]}, //
	    {m1[2][0]-m2[2][0], m1[2][1]-m2[2][1], m1[2][2]-m2[2][2]}, //
	    {m1[3][0]-m2[3][0], m1[3][1]-m2[3][1], m1[3][2]-m2[3][2]}, //
	    {m1[4][0]-m2[4][0], m1[4][1]-m2[4][1], m1[4][2]-m2[4][2]}, //
    };
    final double[][] res_minusTimes_I      = {
	    {m1[0][0]-s*m2[0][0], m1[0][1]-s*m2[0][1], m1[0][2]-s*m2[0][2]}, //
	    {m1[1][0]-s*m2[1][0], m1[1][1]-s*m2[1][1], m1[1][2]-s*m2[1][2]}, //
	    {m1[2][0]-s*m2[2][0], m1[2][1]-s*m2[2][1], m1[2][2]-s*m2[2][2]}, //
	    {m1[3][0]-s*m2[3][0], m1[3][1]-s*m2[3][1], m1[3][2]-s*m2[3][2]}, //
	    {m1[4][0]-s*m2[4][0], m1[4][1]-s*m2[4][1], m1[4][2]-s*m2[4][2]}, //
    };
    
    // minus  and minusEquals (Matrix - Matrix) 
    assertTrue(almostEquals(res_minus_I, minus(m1,m2), EPSILON));
    assertTrue(almostEquals(res_minus_I, minusEquals(copy(m1), m2), EPSILON));

    // minusTimes() and minusTimesEquals()
    assertTrue(almostEquals(res_minusTimes_I, minusTimes(m1, m2, s), EPSILON));
    assertTrue(almostEquals(res_minusTimes_I, minusTimesEquals(copy(m1), m2, s), EPSILON));
  }
  
  /**
   * Testing the Matrix,Scalar multiplication methods times, timesEquals of VMath class. 
   */
  @Test
  public void testMatrixScalarMultiplication() {
    // TODO: comment
    final double[][] m1 = TESTMATRIX;
    
    final double[][] m1_times_one_third = {
        {1/3*m1[0][0], 1/3*m1[0][1], 1/3*m1[0][2], 1/3*m1[0][3], 1/3*m1[0][4]}, //
        {1/3*m1[1][0], 1/3*m1[1][1], 1/3*m1[1][2], 1/3*m1[1][3], 1/3*m1[1][4]}, //
        {1/3*m1[2][0], 1/3*m1[2][1], 1/3*m1[2][2], 1/3*m1[2][3], 1/3*m1[2][4]}, //
        {1/3*m1[3][0], 1/3*m1[3][1], 1/3*m1[3][2], 1/3*m1[3][3], 1/3*m1[3][4]}, //
    };
    
    assertTrue(almostEquals(m1_times_one_third, times(m1, 1/3), EPSILON));
    assertTrue(almostEquals(m1_times_one_third, timesEquals(m1, 1/3), EPSILON));
    
    final double[][] m1_times_zero = new double[m1.length][m1[0].length]; clear(m1_times_zero);

    assertThat(times(m1, 0), is(equalTo(m1_times_zero )));
    assertThat(timesEquals(m1, 0), is(equalTo(m1_times_zero )));
    
    assertThat(times(m1, 1), is(equalTo(m1)));
    assertThat(timesEquals(m1, 1), is(equalTo(m1)));
  }
  
  /**
   * Testing the Matrix, Matrix multiplications methods of VMath class.
   * <p>
   * The following VMath methods are tested:<br>
   * times, transposeTimesTranspose, timesTranspose, transposeTimes
   */
  @Test
  public void testMatrixMatrixMultiplication() {
    // testing times and  transposedTimestransposed
    final double[][] m1 = {
        {1.21, 2000}, // 
        {0   ,-1   }, //
        {7   , 0   }  //
    };
    
    final double[][] m2 = {
        {-1.21, 2000, 2  }, //
        {-700 ,-2368, 4.3}  // 
    };
    
    final double[][] res_times = {
      {m1[0][0]*m2[0][0]+m1[0][1]*m2[1][0], m1[0][0]*m2[0][1]+m1[0][1]*m2[1][1], m1[0][0]*m2[0][2]+m1[0][1]*m2[1][2]},    
      {m1[1][0]*m2[0][0]+m1[1][1]*m2[1][0], m1[1][0]*m2[0][1]+m1[1][1]*m2[1][1], m1[1][0]*m2[0][2]+m1[1][1]*m2[1][2]},    
      {m1[2][0]*m2[0][0]+m1[2][1]*m2[1][0], m1[2][0]*m2[0][1]+m1[2][1]*m2[1][1], m1[2][0]*m2[0][2]+m1[2][1]*m2[1][2]},    
    };
    final double[][] res_transTimesTrans = {
      {m1[0][0]*m2[0][0]+m1[1][0]*m2[0][1]+m1[2][0]*m2[0][2], m1[0][0]*m2[1][0]+m1[1][0]*m2[1][1]+m1[2][0]*m2[1][2]},
      {m1[0][1]*m2[0][0]+m1[1][1]*m2[0][1]+m1[2][1]*m2[0][2], m1[0][1]*m2[1][0]+m1[1][1]*m2[1][1]+m1[2][1]*m2[1][2]},
    };
    
    assertThat(times(m1, m2), is(equalTo(res_times)));
    assertThat(transposeTimesTranspose(m2, m1), is(equalTo(transpose(res_times))));
    
    assertThat(transposeTimesTranspose(m1, m2), is(equalTo(res_transTimesTrans)));
    assertThat(times(m2, m1), is(equalTo(transpose(res_transTimesTrans))));
    
    
    // general testing and testing transposeTimes and timesTranspose
    final double[][] m3 = TESTMATRIX;
    final double[][] m3_t = transpose(m3);
    final double[][] m4 = {
        { 5,  0,  4,  3,  1}, //
        { 9, -3,  2,  8,  8}, //
        {-4, -1,  4,  9, -9}, //
        { 1,  1,  7,  5,  7}, //
    };
    final double[][] m4_t = transpose(m4);
    
    // check times(Matrix, id) = Matrix = times(id, Matrix) 
    assertThat(times(m3, unitMatrix(5) ), is(equalTo(m3)));
    assertThat(times(unitMatrix(4), m3 ), is(equalTo(m3)));
    
    // check transposeTimesTranspose(Matrix, id) = transpose(Matrix) = transposeTimesTranspose(id, Matrix) 
    assertThat(transposeTimesTranspose(m3, unitMatrix(4) ), is(equalTo(m3_t)));
    assertThat(transposeTimesTranspose(unitMatrix(5), m3 ), is(equalTo(m3_t)));
    
    final double[][] m3_times_m4transposed = { 
        {transposeTimes(m3[0], m4[0]), transposeTimes(m3[0], m4[1]), transposeTimes(m3[0], m4[2]), transposeTimes(m3[0], m4[3]) },
        {transposeTimes(m3[1], m4[0]), transposeTimes(m3[1], m4[1]), transposeTimes(m3[1], m4[2]), transposeTimes(m3[1], m4[3]) },
        {transposeTimes(m3[2], m4[0]), transposeTimes(m3[2], m4[1]), transposeTimes(m3[2], m4[2]), transposeTimes(m3[2], m4[3]) },
        {transposeTimes(m3[3], m4[0]), transposeTimes(m3[3], m4[1]), transposeTimes(m3[3], m4[2]), transposeTimes(m3[3], m4[3]) } };
    
    // check timesTranspose without not using a matrix methods times
    assertThat(timesTranspose(m3, m4) , is(equalTo(m3_times_m4transposed)));
    
    // check timesTranspose without not using a vector method transposeTimes
    // this is at the same time a test for the times method assuming the test before succeeded.
    assertThat(timesTranspose(m3, m4) , is(equalTo(times(m3, m4_t))));
    // and the following analog a test for the transposeTimesTranspose method
    assertThat(timesTranspose(m3, m4) , is(equalTo(transposeTimesTranspose(m3_t, m4))));
    
    
    final double[][] m3transposed_times_m4 = {
        {transposeTimes(m3_t[0], m4_t[0]), transposeTimes(m3_t[0], m4_t[1]), transposeTimes(m3_t[0], m4_t[2]), transposeTimes(m3_t[0], m4_t[3]), transposeTimes(m3_t[0], m4_t[4]) },
        {transposeTimes(m3_t[1], m4_t[0]), transposeTimes(m3_t[1], m4_t[1]), transposeTimes(m3_t[1], m4_t[2]), transposeTimes(m3_t[1], m4_t[3]), transposeTimes(m3_t[1], m4_t[4]) },
        {transposeTimes(m3_t[2], m4_t[0]), transposeTimes(m3_t[2], m4_t[1]), transposeTimes(m3_t[2], m4_t[2]), transposeTimes(m3_t[2], m4_t[3]), transposeTimes(m3_t[2], m4_t[4]) },
        {transposeTimes(m3_t[3], m4_t[0]), transposeTimes(m3_t[3], m4_t[1]), transposeTimes(m3_t[3], m4_t[2]), transposeTimes(m3_t[3], m4_t[3]), transposeTimes(m3_t[3], m4_t[4]) },
        {transposeTimes(m3_t[4], m4_t[0]), transposeTimes(m3_t[4], m4_t[1]), transposeTimes(m3_t[4], m4_t[2]), transposeTimes(m3_t[4], m4_t[3]), transposeTimes(m3_t[4], m4_t[4]) } };
 
    // check transposeTimes without not using a matrix methods times
    // without transpose and times
    assertThat(transposeTimes(m3, m4) , is(equalTo(m3transposed_times_m4)));
    
    // check transposeTimes without using a vector method timesTransposed
    // this is as well a test for the times method assuming the test before succeeded.
    assertThat(transposeTimes(m3, m4) , is(equalTo(times(m3_t, m4))));
    // and the following analog a test for the transposeTimesTranspose method
    assertThat(transposeTimes(m3, m4) , is(equalTo(transposeTimesTranspose(m3, m4_t))));
  }
  
  /**
   * Testing the Matrix, Vector multiplications methods of VMath class. 
   * <p>
   * The following VMath methods are tested:<br>
   * times(matrix, vector), times(vector, matrix), transposeTimes(matrix, vector), transposeTimes(vector, matrix),
   * timesTranspose(vector, matrix), transposeTimesTimes(vector, matrix, vector)
   */
  @Test
  public void testMatrixVectorMultiplication() {
    final double delta = 1E-11;
    
    final double[] v1 = {1.21, 2}, v2 = {3, 0.5, -3};
    final double[][] m1 = {
        {1.21, 2000}, //
        {0   ,-1   }, //
        {7   , 0   }  //
    };
    
    final double[] res_times = { m1[0][0]*v1[0] + m1[0][1]*v1[1], m1[1][0]*v1[0] + m1[1][1]*v1[1], m1[2][0]*v1[0] + m1[2][1]*v1[1],};
    final double[] res_transposeTimes = { m1[0][0]*v2[0] + m1[1][0]*v2[1] + m1[2][0]*v2[2], m1[0][1]*v2[0] + m1[1][1]*v2[1] + m1[2][1]*v2[2]};
    final double res_transposeTimesTimes = (m1[0][0]*v2[0]+m1[1][0]*v2[1]+m1[2][0]*v2[2])*v1[0]+ (m1[0][1]*v2[0]+m1[1][1]*v2[1]+m1[2][1]*v2[2])*v1[1];
    
    // testing times(m1, v2)
    assertArrayEquals(res_times, times(m1, v1), delta);
    assertArrayEquals(transpose(m1)[0], times(m1, unitVector(2, 0)) , delta);
    assertArrayEquals(transpose(m1)[1], times(m1, unitVector(2, 1)) , delta);

    // testing transposeTimes(m1, v2);
    assertArrayEquals(res_transposeTimes, transposeTimes(m1, v2), delta);
    assertArrayEquals(m1[0], transposeTimes(m1, unitVector(3, 0)) , delta);
    assertArrayEquals(m1[1], transposeTimes(m1, unitVector(3, 1)) , delta);
    assertArrayEquals(m1[2], transposeTimes(m1, unitVector(3, 2)) , delta);
    
    // testing transposeTimesTimes(a, B, c);
    assertEquals(res_transposeTimesTimes, transposeTimesTimes(v2, m1, v1), delta);
    assertEquals(transposeTimes(v2, times(m1, v1)), transposeTimesTimes(v2, m1, v1), delta);
    assertEquals(times(transposeTimes(v2, m1),  v1)[0], transposeTimesTimes(v2, m1, v1) , delta);
    
    // testing transposedTimes(vector, Matrix) via transpose(transposeTimes(matrix, vector))
    assertThat(transposeTimes(unitVector(3, 0), m1), is(equalTo(transpose(transposeTimes(m1, unitVector(3, 0))))));
    assertThat(transposeTimes(unitVector(3, 1), m1), is(equalTo(transpose(transposeTimes(m1, unitVector(3, 1))))));
    assertThat(transposeTimes(unitVector(3, 2), m1), is(equalTo(transpose(transposeTimes(m1, unitVector(3, 2))))));
    
    // TODO new method VectorVector Multiplication -> maybe in test timesTranspose Vector?
    final double[] v3 = {1,2,-7};
    final double[] v4 = {-5,1,3,-4};
    final double[][] m2 = {v4};
    final double[][] m2_t = {{v4[0]}, {v4[1]}, {v4[2]}, {v4[3]}};

    final double[][] res = {
        {v3[0]*v4[0], v3[0]*v4[1], v3[0]*v4[2], v3[0]*v4[3]}, //
        {v3[1]*v4[0], v3[1]*v4[1], v3[1]*v4[2], v3[1]*v4[3]}, //
        {v3[2]*v4[0], v3[2]*v4[1], v3[2]*v4[2], v3[2]*v4[3]}, //
    };
    
    // testing times(vector, matrix)
    assertThat(times(v3, m2), is(equalTo(res)));
    // via timesTranspose(vector,vector)
    assertThat(times(v3, m2), is(equalTo(timesTranspose(v3, v4))));

    // testing timesTranspose(vector, matrix)
    assertThat(timesTranspose(v3, m2_t), is(equalTo(res)));
    // via timesTranspose(vector,vector) 
    assertThat(timesTranspose(v3, m2_t), is(equalTo(timesTranspose(v3, v4))));
    // TODO Vortrag: Warum so viele Verschieden Multiplicationen Querverweise.   
  }
  


  
  /**
   * Testing the project(vector, Matrix) method of VMath class.
   */
  @Test
  public void testProject() {
    final double[][] m1 = {
        {1, 0},
        {0, 1},
        {0, 0},};
    
    // testvector v1 is already in subspace
    final double[] v1 = {-8,9,0};
    assertThat(project(v1, m1), is(equalTo(v1)));
    
    // testvector v2 is orthogonal to subspace
    final double[] v2 = {0,0,-1};
    final double[] pv2= {0,0,0};
    assertThat(project(v2, m1), is(equalTo(pv2)));
    
    // third testcase FIXME: question more difficult example needed?
    final double[] v3 =  {4,  3, 13};
    final double[] pv3 = {4,  3, 0};
    assertThat(project(v3, m1), is(equalTo(pv3)));
    
  }
  
  /**
   * Testing that *Equals Matrix-operations of the {@link VMath} 
   * class work in place and testing that other matrix-operations
   * create a new instance.
   * 
   * Tests of matrix-methods where the class of the instance 
   * returned differs form class of input method are reasonably omitted,
   * when testing reference.
   * <p>
   * For a complete list of tested methods: <br>
   */
  @Test
  public void testReference() {
    final double[][] m1 = {{1}};
    final double[] inner = {1};
    final double[][] m2 = {inner};

    // testing the methods as in testInverse
    assertNotSame(m1, inverse(m1));
    
    // testing the methods as in testMatrixMatrixMultiplication
    assertNotSame(m1, times(m1,m1));
    
    assertNotSame(m1, transposeTimesTranspose(m1, m1));
    
    assertNotSame(m1, timesTranspose(m1,m1));
    
    assertNotSame(m1, transposeTimes(m1,m1));
    
    // testing the methods as in testMatrixScalarMultiplication 
    final double s1 = 1;
    assertNotSame(m1, times(m1, s1));
    assertSame(m1, timesEquals(m1, s1));
    
    // testing the methods as in testTranspose
    assertNotSame(m1, transpose(m1));
    
    // testing the methods as in testMatrixVectorMultiplication
    final double[] v1 = {1};
    
    assertNotSame(m1, times(v1, m1));
    assertNotSame(m1, times(m1, v1));
    
    assertNotSame(m1, transposeTimes(m1, v1));
    assertNotSame(m1, transposeTimes(v1, m1));
    
    assertNotSame(m1, timesTranspose(v1, m1));
    // transposeTimesTimes reasonably omitted
    
    // testing the methods as in testMinus
    assertNotSame(m1, minus(m1, m2));
    assertSame(m1, minusEquals(m1, m2));
    
    assertNotSame(m1, minusTimes(m1, m1, s1));
    assertSame(m1, minusTimesEquals(m1, m1, s1));
    
    // testing the methods as in testPlus
    assertNotSame(m1, plus(m1, m2));
    assertSame(m1, plusEquals(m1, m2));
    
    assertNotSame(m1, plusTimes(m1, m1, s1));
    assertSame(m1, plusTimesEquals(m1, m1, s1));

    // testing the methods as in testSolve omitted
    
    // testing the methods as in testTraspose
    assertNotSame(m1, transpose(m1));
    

  }
  
  /**
   * Testing that correct Error is raised when dimension of the input data mismatch the needs of the method.
   */
  @Test
  public void testDimensionMismatch() {
    
    // testing the appendColums method
    assertDimensionMismatch("m.getRowDimension() != column.getRowDimension()", () -> appendColumns(identity(3, 2), identity(2, 2)));
    
    // testing the methods as in testInverse omitted

    // testing the methods as in testMatrixMatrixMultiplication
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> times(unitMatrix(3), identity(2, 3)));
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimesTranspose(unitMatrix(3), identity(3, 2)));
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> timesTranspose(unitMatrix(3), identity(3, 2)));
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimes(unitMatrix(3), identity(2, 3)));

    // testing the methods as in testMatrixScalarMultiplication omitted

    // testing the methods as in testTranspose omitted

    // testing the methods as in testMatrixVectorMultiplication
    // vector length and number of rows in matrix differ
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> times(identity(2, 3), unitVector(2, 0)));
    
    // vector length and number of columns in matrix differ
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimes(identity(2, 3), unitVector(3, 0)));
    
    // first vector has wrong length
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimesTimes(unitVector(3, 0), identity(2, 3), unitVector(3, 0)));
    // second vector has wrong length
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimesTimes(unitVector(2, 0), identity(2, 3), unitVector(2, 0)));
    
    // matrix has more than one row
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> times(unitVector(3, 0), identity(2, 3)));

    // matrix has more than one column
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> timesTranspose(unitVector(3, 0), identity(3, 2)));
    
    // vector length and number of rows in matrix differ
    assertDimensionMismatch(ERR_MATRIX_INNERDIM, () -> transposeTimes(unitVector(3, 0), identity(2, 3)));

    // testing the methods as in testMinus
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> minus(identity(3, 3), identity(2, 3)));
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> minusEquals(identity(2, 2), identity(2, 3)));
    
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> minusTimes(identity(3, 3), identity(2, 3), 1));
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> minusTimesEquals(identity(2, 2), identity(2, 3), 1));

    // testing the methods as in testPlus
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> plus(identity(3, 3), identity(2, 3)));
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> plusEquals(identity(2, 2), identity(2, 3)));
    
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> plusTimes(identity(3, 3), identity(2, 3), 1));
    assertDimensionMismatch(ERR_MATRIX_DIMENSIONS, () -> plusTimesEquals(identity(2, 2), identity(2, 3), 1));
    
    // testing the methods as in testSolve omitted
    
    // testing the methods as in testTraspose omitted
    
  }

}