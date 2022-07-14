package com.coolgame;

import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.SecretKey;

public class Game {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        SecureRandom computer = new SecureRandom();
        Referee referee = new Referee(args);
        String example = "\nExample: \"rock scissors paper lizard Spock\"\n";

        if (args.length < 3) { // Check if number of arguments is more than 2.
            System.out.println("\nNot enough arguments. 3 requied, " + args.length + " provided." + example);
            System.exit(1);
        }
        if (args.length % 2 == 0) { // Check if the number of arguments is an odd number.
            System.out.println("\nNumber of arguments must be odd. " + args.length + " provided." + example);
            System.exit(1);
        }
        for (int i = 0; i < args.length - 1; i++) { // Check if arguments are unique.
            for (int j = i + 1; j < args.length; j++) {
                if (args[i].equals(args[j])) {
                    System.out.println("\nArguments must be unique." + example);
                    System.exit(1);
                }
            }
        }

        int computerMove = computer.nextInt(args.length); // Move of the computer is already specified when the game starts.

        FairKey fairKey = new FairKey();
        SecretKey key = fairKey.getKey();
        FairHMAC fairHMAC = new FairHMAC();
        byte[] hmac = fairHMAC.getHmac(key, args[computerMove].getBytes());

        fairHMAC.showHmac(hmac);
        int playerMove = 0;
        while (playerMove == 0) {
            referee.showCommandsList();
            playerMove = referee.getCommand(scanner);
        }
        System.out.println("Computer plays \"" + args[computerMove] + '"');
        referee.haveMatch(computerMove + 1, playerMove, args.length);

        fairKey.showKey(key);
    }
}
