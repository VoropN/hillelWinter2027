package hw_06.obstacle;

import hw_06.participant.Participant;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
        super("Стіна");
    }

    @Override
    public boolean overcome(Participant participant) {
        return participant.jump(getName(), height);
    }
}
