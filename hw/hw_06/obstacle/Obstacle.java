package hw_06.obstacle;

import hw_06.Definable;

// 3. Створіть тип даних Перешкода та кілька її реалізацій: Бігова Доріжка та Стіна.
abstract public class Obstacle implements Definable {
    private String name;

    public String getName() {
        return name;
    }

    public Obstacle(String name) {
        this.name = name;
    }
}
