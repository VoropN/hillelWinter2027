package hw_06.participant;

public class Human extends Participant {
    public Human(String name) {
        super("Людина " + name, 100, 1);
    }

    public Human(String name, int maxRun, int maxJump) {
        super("Людина " + name, maxRun, maxJump);
    }
}
