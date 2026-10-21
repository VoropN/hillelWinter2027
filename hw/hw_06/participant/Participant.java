package hw_06.participant;

// 1. Створіть тип даних Учасник та кілька його реалізацій: Людина, Кіт, Робот.
// 2. Класи з п.2 повинні мати поведінку бігати і стрибати (методи просто виводять інформацію про дію в консоль).
// 4. Класи з п.3 повинні мати поведінку подолати перешкоду (overcome).
public abstract class Participant implements Runner, Jumpable {
    private String name;
    private int maxRun;
    private int maxJump;

    public Participant(String name, int maxRun, int maxJump) {
        this.name = name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    public boolean run(String obstacleName, int distance) {
        if (maxRun >= distance) {
            System.out.printf("Учасник[%s] пробіг перешкоду[%s] на дистанції[%d]м.%n", name, obstacleName, distance);
            return true;
        }
        System.out.printf("Учасник[%s] не пройшов перешкоду[%s] на дистанції[%d]м. Пройдено[%d]м.%n", name, obstacleName, distance, maxRun);
        return false;
    }

    public boolean jump(String obstacleName, int height) {
        if (maxJump >= height) {
            System.out.printf("Учасник[%s] перестрибнув перешкоду[%s] висотою[%d]м.%n", name, obstacleName, height);
            return true;
        }
        System.out.printf("Учасник[%s] не пройшов перешкоду[%s] висотою[%d]м. Пройдено[%d]м.%n", name, obstacleName, height, maxJump);
        return false;
    }

    // 5. Налагодити взаємодію між Учасником та Перешкодою.
    // Іншими словами, учасники під час проходження через перешкоди мають виконувати відповідні дії (бігти чи стрибати).
    // Наприклад: Людина пробігла бігову доріжку.
    // 9. * Результат виконання учасником конкретної перешкоди виводити у консоль. Наприклад: "Учасник[ІМ'Я] пройшов перешкоду[НАЗВА] на дистанції[ЗНАЧЕННЯ]"
    //"Учасник[ІМ'Я] не пройшов перешкоду[НАЗВА] на дистанції[ЗНАЧЕННЯ]. Пройдено[ЗНАЧЕНИЕ]"
    public void printTourFailure() {
        System.out.printf("Учасник[%s] цілком вибуває з серії перешкод.%n", name);
    }
}
