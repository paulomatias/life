package fr.paulo.life;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int sleepingTime = 200;

        BoardFrame board = new BoardFrame();
        EventQueue.invokeLater(() -> board.setVisible(true));
        while (true) {
            SwingUtilities.updateComponentTreeUI(board);
            Thread.sleep(sleepingTime);
            board.computeNextGeneration();
        }
    }
}
