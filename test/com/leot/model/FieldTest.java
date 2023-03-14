package com.leot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import com.leot.exception.ExplosionException;

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

	@Test
	public void DefaultValueMarkup() {
		assertFalse(field.isMarked());
	}

	@Test
	public void testToggleMarkup() {
		field.toggleMarkup();
		assertTrue(field.isMarked());

		field.toggleMarkup();
		assertFalse(field.isMarked());
	}

	@Test
	public void openDefaultdField() {
		assertTrue(field.openField());
	}

	@Test
	public void openMarckedField() {
		field.toggleMarkup();
		assertFalse(field.openField());
	}

	@Test
	public void openMineAndMarckeddField() {
		field.undermine();
		field.toggleMarkup();
		assertFalse(field.openField());
	}

	@Test
	public void openMinedField() {
		field.undermine();
		assertThrows(ExplosionException.class, () -> {
			field.openField();
		});
	}

	@Test
	public void openFieldWithNeighbor1() {
		Field f22 = new Field(2, 2);
		Field f11 = new Field(1, 1);

		field.addNeighbor(f22);
		f22.addNeighbor(f11);

		field.openField();

		assertTrue(f22.isOpen());
		assertTrue(f11.isOpen());
	}

	@Test
	public void openFieldWithNeighbor2() {
		Field f22 = new Field(2, 2);
		Field f11 = new Field(1, 1);
		Field f12 = new Field(1, 2);

		f12.undermine();

		field.addNeighbor(f22);
		f22.addNeighbor(f11);
		f22.addNeighbor(f12);

		field.openField();

		assertTrue(f22.isOpen() && !f11.isOpen());
		assertFalse(f12.isOpen());
	}

	@Test
	public void goalCompleted1() {
		field.undermine();
		field.toggleMarkup();

		assertTrue(field.completedGoal());

		field.toggleMarkup();
		assertFalse(field.completedGoal());
	}

	@Test
	public void goalCompleted2() {
		field.openField();
		assertTrue(field.completedGoal());

		field.undermine();
		assertFalse(field.completedGoal());
	}

	@Test
	public void numberOfMines() {
		Field f34 = new Field(3, 4);
		Field f32 = new Field(3, 2);
		Field f23 = new Field(2, 3);
		Field f22 = new Field(2, 2);

		f22.undermine();
		f34.undermine();

		field.addNeighbor(f34);
		field.addNeighbor(f32);
		field.addNeighbor(f23);
		field.addNeighbor(f22);

		assertEquals(2, field.numberOfMines());

	}

	@Test
	public void toString1() {
		field.toggleMarkup();
		assertEquals("x", field.toString());
	}

	@Test
	public void toStrin2() {
		Field f34 = new Field(3, 4);
		Field f22 = new Field(2, 2);

		f22.undermine();
		f34.undermine();

		field.addNeighbor(f34);
		field.addNeighbor(f22);

		field.openField();
		assertEquals("2", field.toString());
	}

	@Test
	public void toString3() {
		Field f34 = new Field(3, 4);
		field.addNeighbor(f34);

		field.openField();
		assertEquals(" ", field.toString());
	}

	@Test
	public void toString4() {
		assertEquals("?", field.toString());
	}

}
