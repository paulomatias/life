package fr.paulo.life;

public class Cell {
    private long xCor;
    private long yCor;
    private boolean alive;

    public Cell(long xCor, long yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }

    public Cell(long xCor, long yCor, boolean alive) {
        this.xCor = xCor;
        this.yCor = yCor;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public long getXCor() {
        return xCor;
    }

    public long getYCor() {
        return yCor;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean hasCoordinate(long x, long y) {
        return xCor == x && yCor == y;
    }
}
