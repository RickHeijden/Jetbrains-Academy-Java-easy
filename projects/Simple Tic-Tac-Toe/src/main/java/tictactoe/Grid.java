package tictactoe;

public class Grid {
    private char[][] grid;
    private boolean finished;
    private char winner;

    public Grid(String grid) {
        setGridString(grid);
        finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public char getWinner() {
        return winner;
    }

    public void setGridString(String grid) {
        this.grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid.toCharArray(), (i * 3), this.grid[i], 0, 3);
        }
    }

    public boolean isOccupied(int row, int column) {
        return this.grid[row][column] != Main.EMPTY;
    }

    public void setCell(char symbol, int row, int column) {
        if (isOccupied(row, column)) {
            return;
        }
        this.grid[row][column] = symbol;
        result();
    }

    private void result() {
        this.finished = won(Main.X);
        if (this.finished) {
            this.winner = Main.X;
            return;
        }
        this.finished = won(Main.O);
        if (this.finished) {
            this.winner = Main.O;
            return;
        }
        this.finished = !contains(Main.EMPTY);
    }

    private boolean won(char symbol) {
        for (int i = 0; i < 3; i++) {
            boolean winColumn = true;
            boolean winRow = true;
            for (int j = 0; j < 3; j++) {
                if (winColumn && this.grid[j][i] != symbol) {
                    winColumn = false;
                }
                if (winRow && this.grid[i][j] != symbol) {
                    winRow = false;
                }
            }
            if (winColumn || winRow) {
                return true;
            }
        }

        boolean corners1 = this.grid[0][0] == symbol && this.grid[2][2] == symbol;
        boolean corners2 = this.grid[0][2] == symbol && this.grid[2][0] == symbol;
        return (corners1 || corners2) && this.grid[1][1] == symbol;
    }

    public boolean contains(char symbol) {
        for (char[] row : this.grid) {
            for (char cell : row) {
                if (cell == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row : this.grid) {
            for (char cell : row) {
                builder.append(cell);
            }
        }
        return builder.toString();
    }
}
