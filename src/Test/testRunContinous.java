package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lifePanel.MainPanel;

public class testRunContinous {

		/*
		 * a method called runContinuousFlag() is created in MainPanel Class
		 * which takes size and _maxCount as arguements and returns _r 
		 * also test whether size, _maxCount impact output
		 */
		private static MainPanel panel;
		
		@Before
		public void setUp() {
			panel = new MainPanel(15);
		}
		
		/**
		 * Offer normal integer as arguements for runContinousFlag will 
		 * not result in a change of _r 
		 */
		@Test
		public void testrunContinuous1(){
			int size = 10;
			int maxCount = 1000; 
			int res = panel.runContinuousFlag(size, maxCount);
			assertEquals(1000, res);
		}
		
		/**
		 * Offer maximum integer as arguements for runContinousFlag will 
		 * not result in a change of _r 
		 */
		@Test
		public void testrunContinuous2(){
			int size = 0;
			int maxCount = Integer.MAX_VALUE; 
			int res = panel.runContinuousFlag(size, maxCount);
			assertEquals(1000, res);
		}
		
		/**
		 * Offer both minimum integer and maximum integer as arguements 
		 * for runContinousFlag will not result in a change of _r 
		 */
		@Test
		public void testrunContinuous3(){
			int size = Integer.MIN_VALUE;
			int _maxCount = Integer.MIN_VALUE;
			int output = panel.runContinuousFlag(size, _maxCount);
			assertEquals(1000, output);
		}
		
}
