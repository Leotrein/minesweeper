package com.leot.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import com.leot.exception.ExitException;
import com.leot.exception.ExplosionException;
import com.leot.model.Board;

public class boardConsole {

    private Board board;
    private Scanner scan = new Scanner(System.in);

    public boardConsole(Board board) {
        this.board = board;

        playGame();
    }

    private void printBoard() {
        System.out.println("\n" + board);
    }

    private void playGame() {
        try {
            boolean continuee = true;
            while (continuee) {
                gameLoop();
                System.out.print("\nPlay again (S/n) ? ");
                String decision = scan.nextLine();
                if (decision.equalsIgnoreCase("n")) {
                    continuee = false;
                } else {
                    board.restart();
                }
            }
        } catch (ExitException e) {
            System.err.println("the game is over!");
        } finally {
            scan.close();
        }
    }

    private void gameLoop() {
        try {
            System.out.println("\nMinesweeper game\n");
            System.out.println(
                    "OBS: \"*\" - mine, \"?\" - closed, \"x\" - marked, \"-\" - there is no mine nearby (empty)");
            System.out.println("     the numbers on the top right are the indices (x - line, y - column)");
            System.out.println("More: Enter \"exit\" to exit the game at any time");
            while (!board.victory()) {
                printBoard();

                String digited = inputValue("Enter (x,y): ");
                Iterator<Integer> xy = Arrays.stream(digited.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();

                digited = inputValue("1 - Open or 2 - Toggle markup: ");
                if (digited.equals("1")) {
                    board.open(xy.next(), xy.next());
                } else if (digited.equals("2")) {
                    board.toggleMarkup(xy.next(), xy.next());
                }
            }
            printBoard();
            System.out.println("You win!!");
        } catch (ExplosionException e) {
            printBoard();
            System.err.println("You lose!!");
        }
    }

    private String inputValue(String msg) {
        System.out.print(msg);
        String digited = scan.nextLine();

        if (digited.equalsIgnoreCase("exit")) {
            throw new ExitException();
        }
        return digited;
    }

}
