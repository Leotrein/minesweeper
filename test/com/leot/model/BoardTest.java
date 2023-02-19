package com.leot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BoardTest {

	Board board = new Board(10, 10, 25);

	@Test
	public void generateFields() {
		assertEquals(board.getColumn() * board.getLine(), board.getFields().stream().count());
	}

	@Test
	public void spawnMines() {
		assertEquals(25, board.getFields().stream().filter(f -> f.isMined()).count());
	}
}
