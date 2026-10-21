package hw_06.obstacle;

import hw_06.participant.Participant;

// 3. Створіть тип даних Перешкода та кілька її реалізацій: Бігова Доріжка та Стіна.
abstract public class Obstacle {
    private String name;

    protected String getName() {
        return name;
    }

    public Obstacle(String name) {
        this.name = name;
    }

    public abstract boolean overcome(Participant participant);
}
