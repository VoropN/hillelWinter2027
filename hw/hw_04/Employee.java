package hw_04;

// 2. Створити клас "Співробітник" з полями: ПІБ, посада, email, телефон, вік.
// 3. Конструктор класу повинен заповнювати ці поля під час створення об'єкта.
// Забезпечити інкапсуляцію внутрішніх властивостей класу.
public class Employee {
    private String firstname;
    private String surname;
    private String patronym;
    private String position;
    private String email;
    private String phoneNumber;
    private int age;

    public Employee(String firstname, String surname, String patronym, String position, String email, String phoneNumber, int age) {
        this.firstname = firstname;
        this.surname = surname;
        this.patronym = patronym;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronym() {
        return patronym;
    }

    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", patronym='" + patronym + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }
}
