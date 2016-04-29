package no.jenjon13.reeddit.data.entities;

import no.jenjon13.reeddit.data.constraints.Age;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Age(min = 18)
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
