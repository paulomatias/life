package fr.paulo.life;

public class Cell {
    private boolean alive = false;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive() {
        this.alive = true;
    }
}
