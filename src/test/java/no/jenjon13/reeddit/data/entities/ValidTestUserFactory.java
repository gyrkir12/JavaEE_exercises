package no.jenjon13.reeddit.data.entities;

import no.jenjon13.reeddit.data.entities.embeddables.Address;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValidTestUserFactory {
    // TODO replace with JavaFaker/Fariry

    public static SiteUser create() {
        final SiteUser siteUser = new SiteUser();
        siteUser.setFirstName("Jonas");
        siteUser.setSurname("Jensen");

        final Calendar calendarInstance = GregorianCalendar.getInstance();
        calendarInstance.set(1992, Calendar.DECEMBER, 24);
        siteUser.setDateOfBirth(calendarInstance.getTime());

        siteUser.setDateOfRegistration(new Date());

        final Address address = new Address();
        address.setCountry("Norway");
        address.setCity("Oslo");
        address.setStreet("John Colletts Allé 110 PB.150");
        siteUser.setAddress(address);

        siteUser.setUsername("jenjon13");
        siteUser.setPassword("passw0rd");

        return siteUser;
    }
}
