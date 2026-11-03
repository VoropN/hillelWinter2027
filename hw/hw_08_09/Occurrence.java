package hw_08_09;

public class Occurrence {
    String name;
    int occurrence;

    Occurrence(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Occurrence{" +
                "name='" + name + '\'' +
                ", occurrence=" + occurrence +
                '}';
    }
}
