package hw_06;

import hw_06.obstacle.Obstacle;
import hw_06.obstacle.Racetrack;
import hw_06.obstacle.Wall;
import hw_06.participant.Cat;
import hw_06.participant.Human;
import hw_06.participant.Participant;
import hw_06.participant.Robot;

public class Program {
    // 6. Створіть два масиви: з учасниками та перешкодами.
    public static void main(String[] args) {
        Participant[] participants = {
                new Human("Петро"),
                new Cat("Мурзик"),
                new Human("Василь", 100, 3),
                new Robot("Сталь_2000"),
                new Cat("Бублик", 100, 4)
        };
        Obstacle[] obstacles = {
                new Racetrack(10),
                new Wall(1),
                new Racetrack(100),
                new Wall(3)
        };

        overcome(participants, obstacles);
    }

    // 7. Кожен елемент з масиву з учасниками повинен пройти кожну перешкоду з масиву з перешкодами.
    static void overcome(Participant[] participants, Obstacle[] obstacles) {
        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                // 8. Якщо учасник не зміг пройти одну з перешкод, то наступного він не приступає (цілком вибуває з участі в серії перешкод).
                if (!obstacle.overcome(participant)) {
                    participant.printTourFailure();
                    break;
                }
            }
        }
    }
}
