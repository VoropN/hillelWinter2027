package hw_06.participant;

public class Robot extends Participant {
    public Robot(String name) {
        super("Робот " + name, 10_000, 3);
    }

    public Robot(String name, int maxRun, int maxJump) {
        super("Робот " + name, maxRun, maxJump);
    }
}
