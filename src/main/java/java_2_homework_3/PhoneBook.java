package java_2_homework_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBook {
    final Map<String, Set<String>> phoneBook;
    final Set<String> phoneList;

    PhoneBook(){
        phoneBook = new HashMap<>();
        phoneList = new HashSet<>();
    }

    void add(String surname, String phone) {
        if (this.phoneList.contains(phone)) {
//      иначе один и тот же телефон может быть под разными фамилиями
            return;
        }
        Set<String> phoneList = phoneBook.getOrDefault(surname, new HashSet<>());
        phoneList.add(phone);
        phoneBook.put(surname, phoneList);
        this.phoneList.add(phone);
    }

    Set<String> get(String surname){
        return phoneBook.get(surname);
    }
}
