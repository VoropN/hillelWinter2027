package hw_06.participant;

import hw_06.Definable;
import hw_06.obstacle.Obstacle;
import hw_06.obstacle.Racetrack;
import hw_06.obstacle.Wall;

// 1. Створіть тип даних Учасник та кілька його реалізацій: Людина, Кіт, Робот.
// 2. Класи з п.2 повинні мати поведінку бігати і стрибати (методи просто виводять інформацію про дію в консоль).
// 4. Класи з п.3 повинні мати поведінку подолати перешкоду (overcome).
public abstract class Participant implements Definable {
    private String name;
    private int maxRun;
    private int maxJump;

    public String getName() {
        return name;
    }

    public Participant(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    private boolean run(Racetrack obst) {
        int distance = obst.getLength();
        if (maxRun >= distance) {
            System.out.printf("Учасник[%s] пробіг перешкоду[%s] на дистанції[%d]м.%n", name, obst.getName(), distance);
            return true;
        }
        System.out.printf("Учасник[%s] не пройшов перешкоду[%s] на дистанції[%d]м. Пройдено[%d]м.%n", name, obst.getName(), distance, maxRun);
        return false;
    }

    private boolean jump(Wall obst) {
        int height = obst.getHeight();
        if (maxJump >= height) {
            System.out.printf("Учасник[%s] перестрибнув перешкоду[%s] висотою[%d]м.%n", name, obst.getName(), height);
            return true;
        }
        System.out.printf("Учасник[%s] не пройшов перешкоду[%s] висотою[%d]м. Пройдено[%d]м.%n", name, obst.getName(), height, maxJump);
        return false;
    }

    // 5. Налагодити взаємодію між Учасником та Перешкодою.
    // Іншими словами, учасники під час проходження через перешкоди мають виконувати відповідні дії (бігти чи стрибати).
    // Наприклад: Людина пробігла бігову доріжку.
    // 9. * Результат виконання учасником конкретної перешкоди виводити у консоль. Наприклад: "Учасник[ІМ'Я] пройшов перешкоду[НАЗВА] на дистанції[ЗНАЧЕННЯ]"
    //"Учасник[ІМ'Я] не пройшов перешкоду[НАЗВА] на дистанції[ЗНАЧЕННЯ]. Пройдено[ЗНАЧЕНИЕ]"
    public boolean overcome(Obstacle obst) {
        if (obst instanceof Racetrack) {
            return run((Racetrack) obst);
        } else if (obst instanceof Wall){
            return jump((Wall) obst);
        }

        System.out.printf("Учасник[%s] не знає як подолати перешкоду[%s].%n", name, obst.getName());
        return false;
    }

    public void printTourFailure() {
        System.out.printf("Учасник[%s] цілком вибуває з серії перешкод.%n", name);
    }
}
