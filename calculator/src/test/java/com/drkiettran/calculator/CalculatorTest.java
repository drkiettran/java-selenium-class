package com.drkiettran.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

	@Test
	void testAdd() {
		Calculator calc = new Calculator();
		assertEquals(2, calc.add(1,1));
		assertEquals(3, calc.add(1,2));
	}
	
	@Test
	void testSub() {
		Calculator calc = new Calculator();
		assertEquals(0, calc.sub(1,1));
		assertEquals(1, calc.sub(2, 1));
	}
}
