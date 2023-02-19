package com.leot.app;

import com.leot.model.Board;
import com.leot.view.boardConsole;

public class Program {

	public static void main(String[] args) {

		Board board = new Board(6, 6, 6);

		new boardConsole(board);

	}

}
