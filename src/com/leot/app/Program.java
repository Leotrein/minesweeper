package com.leot.app;

import com.leot.model.Board;
import com.leot.view.boardConsole;

public class Program {

	public static void main(String[] args) {

		/*
		 * to play just set the size of the board (line * column) and the amount of
		 * bombs in the Board() constructor.
		 */

		Board board = new Board(10, 10, 25);
		new boardConsole(board);

	}

}
