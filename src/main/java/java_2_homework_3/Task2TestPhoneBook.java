package java_2_homework_3;

public class Task2TestPhoneBook {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ivanov", "888-888-888");
        phoneBook.add("Ivanov", "444-777-444");
        phoneBook.add("Vasiliev", "777-222-444");
        phoneBook.add("Sidorov", "555-111-333");
        phoneBook.add("Sidorov", "888-888-888");

        System.out.printf("%s номер телефона: %s%n","Ivanov", phoneBook.get("Ivanov"));
        System.out.printf("%s номер телефона: %s%n","Vasiliev", phoneBook.get("Vasiliev"));
        System.out.printf("%s номер телефона: %s%n","Sidorov", phoneBook.get("Sidorov"));
    }
}
