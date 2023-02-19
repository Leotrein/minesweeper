package com.leot.view;

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

    private void playGame() {
        try {
            boolean continuee = true;
            while (continuee) {
                gameLoop();
                System.out.print("\nPlay game (S/n) ? ");
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
            while (!board.victory()) {

            }
            System.out.println("You win!!");
        } catch (ExplosionException e) {
            System.err.println("You lose!!");
        }
    }

}
