package Test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lifePanel.MainPanel;

public class testConvertToInt {
 
	    private static Method convertToInt;// Reflected private method
		private static MainPanel panel;
		
		
		/**
		 * Using reflection to prepare the convertToInt() method 
		 * in replace of the private method
		 */
		@BeforeClass
		public static void ReflectedMethod() throws Exception {
			convertToInt = MainPanel.class.getDeclaredMethod("convertToInt", int.class);
			convertToInt.setAccessible(true);
		}

		@Before
		public void setUp() {
			panel = new MainPanel(15);
		}

		/**
		 * Maximum integer can be processed correctly
		 */
		@Test
		public void testMax() throws InvocationTargetException, IllegalAccessException{
			
			int res = (int) convertToInt.invoke(panel, Integer.MAX_VALUE);
			assertEquals(Integer.MAX_VALUE, res);	
		}
		
		/**
		 * Negative integer can be processed correctly
		 */
		@Test
		public void testNegative() throws InvocationTargetException, IllegalAccessException {
			try {
				int res = (int) convertToInt.invoke(panel, -1);
		        fail("Number format exception");
			} catch (IllegalAccessException e) {
				fail(e.getMessage());
			} catch (InvocationTargetException e) {

	        }
		}
		
		/**
		 * Zero can be processed correctly
		 */
		@Test
		public void testZero()  throws InvocationTargetException, IllegalAccessException{
			int res = (int) convertToInt.invoke(panel, 0);
			assertEquals(0, res);	
				
		}
		
		/**
		 * A normal integer can be processed correctly
		 */
		@Test
		public void testNormalNumber() throws InvocationTargetException, IllegalAccessException {
			int res = (int) convertToInt.invoke(panel, 8);
			assertEquals(8, res);			
		}
}
