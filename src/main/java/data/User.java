package data;

import constraints.Age;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = User.allNorwegian, query = "SELECT c from User c WHERE c.address.country LIKE 'Norway'"),
        @NamedQuery(name = User.allFromOslo, query = "SELECT c from User c WHERE c.address.city LIKE 'Oslo'"),
        @NamedQuery(name = User.query_getAll, query = "SELECT u from User u"),
        @NamedQuery(name = User.query_getById, query = "SELECT u from User u WHERE u.id = :" + User.query_idField)
})
@Age(min = 18)
public class User {
    public static final String allNorwegian = "User.allNorwegian";
    public static final String allFromOslo = "User.allFromOslo";
    public static final String query_getAll = "User.all";
    public static final String query_getById = "User.byId";
    public static final String query_idField = "User.id";
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

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;

    @NotNull
    @Embedded
    @Valid
    private Address address;

    @NotNull
    @Size(max = 64)
    private String username;

    @NotNull
    @Size(max = 256)
    private String password;

    @NotNull
    @Size(max = 64)
    private String location;

    @OneToMany
    private List<NewsArticle> newsArticles;

    @OneToMany
    private List<Comment> comments;

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
