package fr.paulo.life;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private int boardSize;
    private int turn = 0;
    private Cell[][] cells;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        cells = new Cell[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    public void setCellCoordinatesAliveRandomly(double percentageOfRandomCell) {
        for (int i = 0; i < boardSize * boardSize * percentageOfRandomCell; i++) {
            int randomX = ThreadLocalRandom.current().nextInt(boardSize);
            int randomY = ThreadLocalRandom.current().nextInt(boardSize);
            setCellAtCoordinateAlive(randomX, randomY);
        }
    }

    public void setCellAtCoordinateAlive(int x, int y) {
        cells[x][y].setAlive();
    }

    public void playTurn() {
        Cell[][] cellsAfterTurn = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cellsAfterTurn[i][j] = new Cell(shouldCellBeAliveNextTime(cells, i, j));
            }
        }
        turn++;
        cells = cellsAfterTurn;
    }

    private boolean shouldCellBeAliveNextTime(Cell[][] cellsAtTurn, int xCor, int yCor) {
        int neighborsNumber = getAliveNeighbors(cellsAtTurn, xCor, yCor).size();
        if (cellsAtTurn[xCor][yCor].isAlive()) {
            return neighborsNumber == 2 || neighborsNumber == 3;
        } else {
            return neighborsNumber == 3;
        }
    }

    private List<Cell> getAliveNeighbors(Cell[][] cellsAtTurn, int x, int y) {
        List<Cell> aliveNeighbors = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i == x && j == y) || i < 0 || i >= boardSize || j < 0 || j >= boardSize) {
                    continue;
                }
                Cell cell = cellsAtTurn[i][j];
                if (cell.isAlive()) {
                    aliveNeighbors.add(cell);
                }
            }
        }
        return aliveNeighbors;
    }

    public String display() {
        StringBuilder res = new StringBuilder("Turn: ").append(turn);
        for (int i = 0; i < boardSize; i++) {
            res.append("\n");
            for (int j = 0; j < boardSize; j++) {
                Cell cell = cells[i][j];
                if (cell.isAlive()) {
                    res.append('1');
                } else {
                    res.append('0');
                }
            }
        }
        String s = res.toString();
        System.out.println(s);
        return s;
    }
}
