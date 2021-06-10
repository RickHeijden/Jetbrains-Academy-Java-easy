package cinema;

import java.util.Scanner;

public class Cinema {

    private Room room;
    private boolean run = true;

    public static void main(String[] args) {
        // Write your code here
        (new Cinema()).run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        this.room = new Room(rows, seatsPerRow);

        while (this.run) {
            menu(scanner);
        }

        scanner.close();
    }

    public void menu(Scanner scanner) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        if (choice == 0) {
            this.run = false;
        } else if (choice == 1) {
            this.room.print();
        } else if (choice == 2) {
            int row;
            int seat;
            do {
                System.out.println("Enter a row number:");
                row = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seat = scanner.nextInt();
            } while(!this.room.claimSeat(row, seat));
        } else if (choice == 3) {
            this.room.printStatistics();
        }
    }
}
