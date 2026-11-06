package hw_10;

import java.util.*;

// 0. Створити клас FileNavigator. Клас призначений для зберігання списку файлів, які розташовані на конкретному шляху.
public class FileNavigator {
    private final Map<String, Map<String, FileData>> map = new HashMap<>();

    // 2. Реалізувати метод add у класі FileNavigator. Цей метод додає файл за вказаним шляхом. Якщо шлях вже існує, новий файл необхідно додати до списку, вже пов'язаному з відповідним шляхом. ВАЖЛИВО: Шлях – унікальне значення і не повинно повторюватися.
    // 7. ** Реалізувати перевірку консистентності у методі add у класі FileNavigator. Не дозволяти додавати значення і повідомити про це в консолі, при спробі додати значення FileData значення шляху якого не відповідає шляху-ключу, що веде до списку файлів.
    public void add(String path, FileData file) throws FilePathConsistencyException {
        if (!path.equals(file.getPath())) {
            FilePathConsistencyException exception = new FilePathConsistencyException(path, file);
            System.err.println(exception.getMessage());
            throw exception;
        }
        map.computeIfAbsent(path, _ -> new HashMap<>()).put(file.getName(), file);
    }

    // 3. Реалізувати метод find у класі FileNavigator. Метод повертає список файлів, пов'язаних зі шляхом переданим як параметр.
    public List<FileData> find(String path) {
        Map<String, FileData> files = map.get(path);
        return files == null ? new ArrayList<>() : new ArrayList<>(files.values());
    }

    // 4. Реалізувати метод filterBySize у класі FileNavigator. Метод повертає список файлів, розмір (в байтах) яких не перевищує значення, передане як параметр.
    public List<FileData> filterBySize(int byteSize) {
        List<FileData> files = new ArrayList<>();
        for (Map<String, FileData> filesByPath : map.values()) {
            for (FileData file : filesByPath.values()) {
                if (file.getByteSize() <= byteSize) {
                    files.add(file);
                }
            }
        }
        return files;
    }

    // 5. Реалізувати метод remove у класі FileNavigator. Метод видаляє шлях і пов'язані з ним файли, виходячи зі значення шляху, переданого як параметр.
    public List<FileData> remove(String path) {
        Map<String, FileData> files = map.remove(path);
        return files == null ? new ArrayList<>() : new ArrayList<>(files.values());
    }

    // 6. * Реалізувати метод sortBySize у класі FileNavigator. Метод сортує всі наявні файли за розміром (за зростанням), потім повертає список відсортованих файлів.
    public List<FileData> sortBySize() {
        List<FileData> files = new ArrayList<>();
        for (Map<String, FileData> filesByPath : map.values()) {
            files.addAll(filesByPath.values());
        }
        files.sort(Comparator.comparingInt(FileData::getByteSize));
        return files;
    }
}
