package fr.paulo.life;

public class Main {

    public static void main(String[] args) {
        long xSize = 100;
        long ySize = 100;
        long numberOfTurns = 10;
        Board board = new Board(xSize, ySize);

        board.setCellCoordinatesAliveRandomly(0.1);

        for (int i = 0; i < numberOfTurns; i++) {
            board.display();
            board.playTurn();
        }
    }
}
