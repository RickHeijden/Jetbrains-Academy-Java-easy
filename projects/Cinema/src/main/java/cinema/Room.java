package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Room {

    private final List<List<String>> seats;
    private final int rows;
    private final int seatsPerRow;
    private int purchased;

    public Room(int rows, int seatsPerRow) {
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seats = createSeats();
        this.purchased = 0;
    }

    public int calculateIncome() {
        int income = 0;
        if (this.rows * this.seatsPerRow <= 60) {
            income = this.purchased * 10;
        } else {
            for (int i = 1; i <= (int) Math.floor(this.rows / 2.0); i++) {
                income += seats.get(i).stream().filter(s -> s.equals("B")).count() * 10;
            }
            for (int i = (int) Math.floor(this.rows / 2.0) + 1; i < seats.size(); i++) {
                income += seats.get(i).stream().filter(s -> s.equals("B")).count() * 8;
            }
        }
        return income;
    }

    public int calculateTotalIncome() {
        int income;
        if (this.rows * this.seatsPerRow <= 60) {
            income = this.rows * this.seatsPerRow * 10;
        } else {
            income = ((this.rows / 2) * this.seatsPerRow * 10) +
                    ((int) Math.ceil(this.rows / 2.0) * this.seatsPerRow * 8);
        }
        return income;
    }

    public int getRowPrice(int row) {
        return (this.rows * this.seatsPerRow <= 60) || (row < (int) Math.ceil(this.rows / 2.0)) ? 10 : 8;
    }

    public boolean claimSeat(int row, int seat) {
        if (row > this.rows || row < 1 || seat > this.seatsPerRow || seat < 1) {
            System.out.println("Wrong input!");
            return false;
        } else if (this.seats.get(row).get(seat).equals("B")) {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            this.seats.get(row).set(seat, "B");
            purchased++;
            System.out.println("Ticket price: $" + getRowPrice(row));
            return true;
        }
    }

    public List<List<String>> createSeats() {
        List<List<String>> seats = new ArrayList<>();
        Stream.iterate(0, x -> x + 1).limit(this.rows + 1).forEach(row -> {
            seats.add(new ArrayList<>());
            seats.get(row).add(row + "");
            Stream.iterate(1, x -> x + 1).limit(this.seatsPerRow).forEach(seat -> {
                boolean added = row == 0 ? seats.get(row).add(seat + "") : seats.get(row).add("S");
            });
        });
        return seats;
    }

    public void print() {
        System.out.println("Cinema:");
        this.seats.forEach(row -> {
            row.forEach(seat -> System.out.print(seat.replaceAll("0", " ") + " "));
            System.out.println();
        });
    }

    public void printStatistics() {
        System.out.printf("Number of purchased tickets: %s%n", purchased);
        System.out.printf("Percentage: %.2f%%%n", (((double) purchased / (double) (rows * seatsPerRow))*100));
        System.out.printf("Current income: $%s%n", calculateIncome());
        System.out.printf("Total income: $%s%n", calculateTotalIncome());
    }
}
