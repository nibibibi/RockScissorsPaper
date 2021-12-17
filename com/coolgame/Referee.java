package com.coolgame;

import java.util.Scanner;

public class Referee {
    String[] moves;
    public Referee(String[] moves) {
        this.moves = moves;
    }
    public int getCommand(Scanner scanner) {
        System.out.print("Enter your move: ");
        try {
            String command = scanner.nextLine();
            if (command.equals("?")) {
                Table table = new Table();
                table.display(moves); 
                return 0;
            }
            if (Integer.parseInt(command) >= 1 && Integer.parseInt(command) <= moves.length) {
                return Integer.parseInt(command);
            } else {
                return 0;
            }
        } catch (Exception e){
            return 0;
        }
    }
    public void showCommandsList() {
        System.out.println("Avaliable moves:");
            for (int i = 1; i <= moves.length; i++) {
                System.out.println(i + " - " + moves[i-1]);
            }
            System.out.println("? - help");
    }
    public void haveMatch(int computerMove, int playerMove, int movesCount) {
        int rightToA = movesCount - playerMove;
        int half = (movesCount - 1) / 2;
        if (playerMove == computerMove) {
            System.out.println("Draw!");
        } else {
            if ((computerMove <= (half - rightToA)) || ((computerMove > playerMove) && (computerMove <= (playerMove + half)))) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
        }
    }
}
