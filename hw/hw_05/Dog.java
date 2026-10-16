package hw_05;

// 2. Створити класи Собака та Кіт з успадкуванням від класу Тварини.
// 3. Наприклад, dogBobik.run(150); -> 'Бобік пробіг 150 м'
// 4. Кожна тварина має обмеження на дії (біг: кіт 200 м., собака 500 м.; плавання: кіт не вміє плавати, собака 10 м.).
// 5. * Додати підрахунок створених котів, собак та тварин.
public class Dog extends Animal {
    private static final int MAX_RUN = 500;
    private static final int MAX_SWIM = 10;
    private static int count = 0;

    public Dog(String name) {
        super(name);
        count++;
    }

    public static int getCount() {
        return count;
    }
    
    @Override
    public void run(int distance) {
        if (isDistanceNegative(distance)) {
            return;
        }
        if (distance > MAX_RUN) {
            System.out.printf("Дію скасовано: отримано дистанцію %d м, але %s може пробігти не більше %d м.%n", distance, getName(), MAX_RUN);
            return;
        }
        System.out.printf("%s пробіг %d м.%n", getName(), distance);
    }

    @Override
    public void swim(int distance) {
        if (isDistanceNegative(distance)) {
            return;
        }
        if (distance > MAX_SWIM) {
            System.out.printf("Дію скасовано: отримано дистанцію %d м, але %s може проплисти не більше %d м.%n", distance, getName(), MAX_SWIM);
            return;
        }
        System.out.printf("%s проплив %d м%n", getName(), distance);
    }
}
