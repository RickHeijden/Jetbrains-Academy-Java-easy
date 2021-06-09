package tictactoe;

import java.util.Scanner;

public class Main {
    public static final char X = 'X';
    public static final char O = 'O';
    public static final char EMPTY = ' ';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grid grid = new Grid("_________".replaceAll("_", String.valueOf(EMPTY)));

        printGrid(grid.toString());

        char symbol = X;
        while (!grid.isFinished()) {
            while (true) {
                System.out.println("Enter the coordinates:");
                try {
                    int row = Integer.parseInt(scanner.next()) - 1;
                    int column = Integer.parseInt(scanner.next()) - 1;

                    if (row < 0 || row > 2 || column < 0 || column > 2) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    } else if (grid.isOccupied(row, column)) {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }
                    grid.setCell(symbol, row, column);
                    break;
                } catch (NumberFormatException ex) {
                    if (scanner.hasNext()) {
                        scanner.next();
                    }
                    System.out.println("You should enter numbers!");
                }
            }
            symbol = symbol == X ? O : X;
            printGrid(grid.toString());
        }
        printGrid(grid.toString());
        printEndMessage(grid);
        scanner.close();
    }

    public static void printGrid(String grid) {
        System.out.println("---------");
        char[] field = grid.toCharArray();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                System.out.print("| ");
            }
            System.out.print(field[i] + " ");
            if (i % 3 == 2) {
                System.out.println("|");
            }
        }
        System.out.println("---------");
    }

    public static void printEndMessage(Grid grid) {
        if (grid.isFinished() && grid.getWinner() == X) {
            System.out.println("X wins");
        } else if (grid.isFinished() && grid.getWinner() == O) {
            System.out.println("O wins");
        } else if (grid.isFinished()) {
            System.out.println("Draw");
        } else {
            System.out.println("Game not finished");
        }
    }
}
