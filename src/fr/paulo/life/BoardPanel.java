package fr.paulo.life;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ThreadLocalRandom;

public class BoardPanel extends JPanel {
    private static final Font TIMES_ROMAN = new Font("TimesRoman", Font.PLAIN, 50);

    private final int ySize;
    private final int xSize;
    private final int squareSize;

    private int generation;
    private boolean[][] cells;

    public BoardPanel(int width, int height) {
        squareSize = Math.min(width, height) / 100;
        xSize = width / squareSize;
        ySize = height / squareSize;
        cells = new boolean[ySize][xSize];

        // Initialize the board with random values
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                cells[i][j] = random.nextBoolean();
            }
        }
    }

    public void computeNextGeneration() {
        boolean[][] cellsAfterTurn = new boolean[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                cellsAfterTurn[i][j] = shouldCellBeAliveOnNextGeneration(cells, i, j);
            }
        }
        generation++;
        cells = cellsAfterTurn;
    }

    private boolean shouldCellBeAliveOnNextGeneration(boolean[][] cellsAtTurn, int xCor, int yCor) {
        int neighborsNumber = getNumberOfAliveNeighbors(cellsAtTurn, xCor, yCor);
        if (cellsAtTurn[xCor][yCor]) {
            return neighborsNumber == 2 || neighborsNumber == 3;
        } else {
            return neighborsNumber == 3;
        }
    }

    private int getNumberOfAliveNeighbors(boolean[][] cellsAtTurn, int x, int y) {
        int aliveNeighbors = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i == x && j == y) || i < 0 || i >= ySize || j < 0 || j >= xSize) {
                    continue;
                }
                boolean cell = cellsAtTurn[i][j];
                if (cell) {
                    aliveNeighbors++;
                }
            }
        }
        return aliveNeighbors;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTheBoard(g);
    }

    private void drawTheBoard(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setFont(TIMES_ROMAN);
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHints(renderingHints);
        graphics2D.setStroke(new BasicStroke(1));

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                boolean cell = cells[i][j];
                if (cell) {
                    graphics2D.setColor(Color.white);
                } else {
                    graphics2D.setColor(Color.black);
                }
                graphics2D.fill(new Rectangle2D.Double(j * squareSize, i * squareSize, squareSize, squareSize));
                graphics2D.setColor(Color.gray);
                graphics2D.draw(new Rectangle2D.Double(j * squareSize, i * squareSize, squareSize, squareSize));
            }
        }
        graphics2D.setColor(Color.red);
        graphics2D.drawString("R:" + this.generation, (float) (2 * squareSize), (float) (4 * squareSize));
    }
}
