package hw_05;

public class Program {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобік");
        dogBobik.run(150);
        dogBobik.run(505);
        dogBobik.run(-505);
        dogBobik.swim(3);
        dogBobik.swim(30);
        new Dog("Шарік");
        new Dog("Jack");

        Cat catMursik = new Cat("Мурзік");
        catMursik.run(3);
        catMursik.run(300);
        catMursik.swim(2);
        catMursik.swim(-1);
        new Cat("Hero");

        System.out.println("Animals created: " + Animal.getCount());
        System.out.println("Cats created: " + Cat.getCount());
        System.out.println("Dogs created: " + Dog.getCount());
    }
}
