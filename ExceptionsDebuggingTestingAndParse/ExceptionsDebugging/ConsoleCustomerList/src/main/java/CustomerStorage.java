import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.Map;


public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws InvalidEmailFormatException {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;



        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong command size");
        }
        String phoneNumber = components[INDEX_PHONE];
        String email = components[INDEX_EMAIL];
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!isValidEmail(components[2])) {
            throw new InvalidEmailFormatException("Invalid email format: " + email);
        }
        if (!components[3].matches("^\\+?\\d{1,2}\\(?\\d{1,4}\\)?\\d{7}$")) {
            throw new IllegalArgumentException("Invalid phone format: " + phoneNumber);
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
    private boolean isValidEmail(String email) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }
}