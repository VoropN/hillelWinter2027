package hw_08_09.phonebook;

import java.util.ArrayList;
import java.util.List;

// 0. Створити новий пакет phonebook.
// 1. Створити клас Телефонний довідник.
// 4. Клас Телефонний Довідник зберігає безліч записів типу даних Запис.
public class PhoneBook {
    private final ArrayList<PhoneRecord> records = new ArrayList<>();

    // 5. У класі Телефонний Довідник реалізувати метод add. Метод додає Запис до Телефонного Довідника.
    public void add(PhoneRecord rec) {
        records.add(rec);
    }

    // 6. У класі Телефонний Довідник реалізувати метод find(). Метод здійснює пошук конкретного запису на ім'я. Якщо запис знайдений (перший знайдений), тоді його необхідно повернути, інакше повертається null.
    public PhoneRecord find(String name) {
        for (PhoneRecord rec : records) {
            if (rec.name().equalsIgnoreCase(name)) {
                return rec;
            }
        }
        return null;
    }

    // 8. * У класі Телефонний Довідник реалізувати метод findAll(). Метод здійснює пошук записів на ім'я. Якщо записи знайдені, тоді їх необхідно повернути, інакше повертається null.
    public List<PhoneRecord> findAll(String name) {
        ArrayList<PhoneRecord> list = new ArrayList<>();
        for (PhoneRecord rec : records) {
            if (rec.name().equalsIgnoreCase(name)) {
                list.add(rec);
            }
        }
        return list.isEmpty() ? null : list;
    }
}

