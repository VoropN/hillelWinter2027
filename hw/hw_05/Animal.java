package hw_05;

// 3. Всі тварини можуть бігти та плисти. Як параметр кожному методу передається довжина перешкоди. Результатом виконання дії буде друк у консоль.
// 5. * Додати підрахунок створених котів, собак та тварин.
public abstract class Animal {
    private final String name;
    private static int count = 0;

    public String getName() {
        return name;
    }

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }

    protected boolean isDistanceNegative(int distance) {
        if (distance < 0) {
            System.out.printf("Дію скасовано: дистанція не може бути від’ємною (%d м).%n", distance);
            return true;
        }
        return false;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);
}
