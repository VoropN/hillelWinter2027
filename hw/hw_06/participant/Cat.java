package hw_06.participant;

public class Cat extends Participant {
    public Cat(String name) {
        super("Кіт " + name, 100, 1);
    }

    public Cat(String name, int maxRun, int maxJump) {
        super("Кіт " + name, maxRun, maxJump);
    }
}
