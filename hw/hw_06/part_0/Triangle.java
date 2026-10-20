package hw_06.part_0;

// 0. Створити 3 класи, які реалізують цей інтерфейс: коло, трикутник, квадрат.
public class Triangle implements Figure {
    private final int base;
    private final int height;

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return (double) base * height / 2;
    }
}
