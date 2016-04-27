package data.user;

import data.Address;
import data.User;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValidUserFactory {
    public static User create() {
        final User user = new User();
        user.setFirstName("Jonas");
        user.setSurname("Jensen");

        final Calendar calendarInstance = GregorianCalendar.getInstance();
        calendarInstance.set(1992, Calendar.DECEMBER, 24);
        user.setDateOfBirth(calendarInstance.getTime());

        user.setDateOfRegistration(new Date());

        final Address address = new Address();
        address.setCountry("Norway");
        address.setCity("Oslo");
        address.setStreet("John Colletts Allé 110 PB.150");
        user.setAddress(address);

        user.setUsername("jenjon13");
        user.setPassword("passw0rd");

        return user;
    }
}
