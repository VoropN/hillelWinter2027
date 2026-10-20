package hw_06.obstacle;

public class Racetrack extends Obstacle {
    // 8. * У перешкод є довжина (для доріжки) або висота (для стіни), а учасників обмеження на біг та стрибки.
    private int length;

    public int getLength() {
        return length;
    }

    public Racetrack(int length) {
        this.length = length;
        super("Бігова доріжка");
    }
}
