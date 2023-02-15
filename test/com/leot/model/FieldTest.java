
package com.leot.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class FieldTest {

	private Field field = new Field(3, 3);

	@Test
	public void neighborDiagonal() {

		assertTrue(field.addNeighbor(new Field(4, 4)));
		assertFalse(field.addNeighbor(new Field(5, 5)));

	}

	@Test
	public void neighborNotDiagonal() {

		assertTrue(field.addNeighbor(new Field(3, 2)));
		assertTrue(field.addNeighbor(new Field(4, 3)));
		assertFalse(field.addNeighbor(new Field(5, 3)));

	}

}
