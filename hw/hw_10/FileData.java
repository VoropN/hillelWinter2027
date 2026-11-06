package hw_10;

// 1. Створити клас FileData. Клас представляє конкретний файл і складається з: ім'я файлу, розміру в байтах, шлях до файлу.
public class FileData {
    private String name;
    private int byteSize;
    private String path;

    public String getPath() {
        return path;
    }

    public int getByteSize() {
        return byteSize;
    }

    public String getName() {
        return name;
    }

    public FileData(String name, int byteSize, String path) {
        this.name = name;
        this.byteSize = byteSize;
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "name='" + name + '\'' +
                ", byteSize=" + byteSize +
                ", path='" + path + '\'' +
                '}';
    }
}
