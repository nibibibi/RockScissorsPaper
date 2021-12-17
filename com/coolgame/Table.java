package com.coolgame;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.Arrays;

public class Table {
    void display(String[] moves) {
        String[][] table = new String[moves.length + 1][moves.length + 1];
        boolean leftJustifiedRows = true;
        for (int i = 0; i <= moves.length; i++) {
            if (i == 0) {
                table [i][0] = "";
                for (int j = 0; j < moves.length; j++) {
                    table[i][j + 1] = moves[j];
                }
            } else {
                table [i][0] = moves[i - 1];
                for (int j = 0; j < moves.length; j++) {
                    if (i == j + 1) {
                        table[i][j + 1] = "Draw";
                    } else {
                        if ((j + 1 <= ((moves.length - 1)/2 - (moves.length - i)) || (j + 1 > i) && (j + 1 <= (i + (moves.length - 1)/2)))) {
                            table[i][j + 1] = "Win";
                        } else {
                            table[i][j + 1] = "Lose";
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
            if (columnLengths.get(i) == null) {
                columnLengths.put(i, 0);
            }
            if (columnLengths.get(i) < a[i].length()) {
                columnLengths.put(i, a[i].length());
            }
        }));

        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
            String templn = "+-";
            templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
                    (a1, b1) -> a1 + b1);
            templn = templn + "-";
            return ln + templn;
        }, (a, b) -> a + b);
        line = line + "+\n";

        System.out.print(line);
        Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
        System.out.print(line);
    
        Stream.iterate(1, (i -> i < table.length), (i -> ++i))
                .forEach(a -> System.out.printf(formatString.toString(), table[a]));
        System.out.print(line);
    }
}
