package fr.paulo.life;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int boardSize = 25;
        double percentageOfRandomCell = 0.2;
        int numberOfTurns = 100_000;

        Board board = new Board(boardSize);
        board.setCellCoordinatesAliveRandomly(percentageOfRandomCell);
        for (int i = 0; i < numberOfTurns; i++) {
            board.display();
            Thread.sleep(1_000);
            board.playTurn();
        }
    }
}
