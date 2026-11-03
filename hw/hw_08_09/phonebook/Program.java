package hw_08_09.phonebook;

public class Program {
    public static void main(String[] args) {
        PhoneBook pbook = new PhoneBook();
        pbook.add(new PhoneRecord("George Dog", "+1(888)-000-0001"));
        pbook.add(new PhoneRecord("Gabriel Fast", "+1(888)-000-0033"));
        pbook.add(new PhoneRecord("Margaret Duo", "+1(888)-000-0002"));
        pbook.add(new PhoneRecord("Gabriel Fast", "+1(888)-000-0003"));
        pbook.add(new PhoneRecord("Michel Peterson", "+1(888)-000-0003"));
        pbook.add(new PhoneRecord("Gabriel Fast", "+1(888)-000-0333"));

        System.out.println("find: " +pbook.find("gabriel Fast"));
        System.out.println("find not existing: " + pbook.find("Jake Slow"));
        System.out.println("findAll: " + pbook.findAll("Gabriel fast"));
        System.out.println("findAll not existing: " + pbook.findAll("Jake Fast"));
    }
}
