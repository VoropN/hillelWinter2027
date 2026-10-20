package hw_06.part_0;

// 0. Створити 3 класи, які реалізують цей інтерфейс: коло, трикутник, квадрат.
public class Square implements Figure {
    private final int side;

    public Square(int side) {
        this.side = side;
    }

    @Override
    public double area() {
        return (double) side * side;
    }
}
