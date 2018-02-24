package fr.paulo.life;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private long xSize;
    private long ySize;
    private long turn = 0;
    private List<Cell> cells = new ArrayList<>();

    public Board(long xSize, long ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        for (long i = 0; i < xSize; i++) {
            for (long j = 0; j < xSize; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    public void setCellCoordinatesAliveRandomly(double percentageOfRandomCell) {
        for (int i = 0; i < xSize * ySize * percentageOfRandomCell; i++) {
            long randomX = ThreadLocalRandom.current().nextLong(xSize + 1);
            long randomY = ThreadLocalRandom.current().nextLong(ySize + 1);
            setCellAtCoordinateAlive(randomX, randomY);
        }
    }

    public void setCellAtCoordinateAlive(long x, long y) {
        Optional<Cell> option = cells.stream().filter(cell -> cell.hasCoordinate(x, y)).findFirst();
        option.ifPresent(cell -> cell.setAlive(true));
    }

    public void playTurn() {
        List<Cell> cellsAfterTurn = new ArrayList<>();
        for (long i = 0; i < xSize; i++) {
            for (long j = 0; j < xSize; j++) {
                final long x = i;
                final long y = j;
                Optional<Cell> option = cells.stream().filter(cell -> cell.hasCoordinate(x, y)).findFirst();
                if (option.isPresent()) {
                    cellsAfterTurn.add(new Cell(x, j, shouldCellBeAliveNextTime(cells, option.get())));
                }
            }
        }
        turn++;
        cells = new ArrayList<>(cellsAfterTurn);
    }

    private boolean shouldCellBeAliveNextTime(List<Cell> cellsAtTurn, Cell cell) {
        int neighborsNumber = getAliveNeighbors(cellsAtTurn, cell.getXCor(), cell.getYCor()).size();
        if (cell.isAlive()) {
            return neighborsNumber == 2 || neighborsNumber == 3;
        } else {
            return neighborsNumber == 3;
        }
    }

    private List<Cell> getAliveNeighbors(List<Cell> cellsAtTurn, long x, long y) {
        List<Cell> aliveNeighbors = new ArrayList<>();
        for (long i = x - 1; i <= x + 1; i++) {
            for (long j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }

                long finalI = i;
                long finalJ = j;
                Optional<Cell> option = cellsAtTurn.stream().filter(cell -> cell.hasCoordinate(finalI, finalJ)).findFirst();

                if (!option.isPresent()) {
                    continue;
                }

                Cell cell = option.get();
                if (cell.isAlive()) {
                    aliveNeighbors.add(cell);
                }
            }
        }
        return aliveNeighbors;
    }

    public String display() {
        StringBuilder res = new StringBuilder("Turn: ").append(turn);
        for (long i = 0; i < xSize; i++) {
            res.append("\n");
            for (long j = 0; j < ySize; j++) {
                final long x = i;
                final long y = j;
                Optional<Cell> option = cells.stream().filter(cell -> cell.hasCoordinate(x, y)).findFirst();
                if (option.isPresent()) {
                    if (option.get().isAlive()) {
                        res.append('1');
                    } else {
                        res.append('0');
                    }
                } else {
                    res.append('X');
                }
            }
        }
        String s = res.toString();
        System.out.println(s);
        return s;
    }
}
