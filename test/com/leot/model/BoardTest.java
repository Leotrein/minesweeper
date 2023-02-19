package com.leot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class BoardTest {

	Board board = new Board(10, 10, 25);

	@Test
	public void generateFields() {
		assertEquals(board.getColumn() * board.getLine(), board.getFields().stream().count());
	}

	@Test
	public void associateNeighbors() {
		Board b1 = new Board(1, 2, 1);
		assertEquals(1, b1.getFields().get(0).getNeighbors().stream().count());

	}

	@Test
	public void spawnMines() {
		assertEquals((long) board.getMines(), board.getFields().stream().filter(f -> f.isMined()).count());
	}

	@Test
	public void restart() {
		List<Field> minesBefore = board.getFields().stream().filter(f -> f.isMined()).collect(Collectors.toList());
		board.restart();
		List<Field> minesAfter = board.getFields().stream().filter(f -> f.isMined()).collect(Collectors.toList());
		assertFalse(minesBefore.containsAll(minesAfter));
	}
}
