package hw_06.obstacle;

public class Wall extends Obstacle {
    private int height;

    public int getHeight() {
        return height;
    }

    public Wall(int height) {
        this.height = height;
        super("Стіна");
    }
}
