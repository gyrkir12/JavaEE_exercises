import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = Customer.allNorwegian, query = "SELECT c from Customer c WHERE c.address.country LIKE 'Norway'"),
        @NamedQuery(name = Customer.allFromOslo, query = "SELECT c from Customer c WHERE c.address.city LIKE 'Oslo'")
})
public class Customer {
    public static final String allNorwegian = "allNorwegian";
    public static final String allFromOslo = "allFromOslo";
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(max = 64)
    private String firstName;

    @Size(max = 64)
    private String middleName;

    @NotNull
    @Size(max = 64)
    private String surname;

    @Past
    private Date dateOfBirth;

    @Past
    private Date dateOfRegistration;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public long getId() {
        return id;
    }
}
