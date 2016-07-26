package ru.kpfu.itis.elvirabatyrova.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Pc on 25.07.2016.
 */

@Entity
@Table(name = "customer", schema = "public", catalog = "payment_system")
public class Customer {

    private Integer id;
    private String firstName;
    private String secondName;
    private Date dateOfBirth;
    private String address;

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Basic
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    @Basic
    @Column(name = "second_name", nullable = false)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondname) {
        this.secondName = secondname;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateofbirth) {
        this.dateOfBirth = dateofbirth;
    }

    @Basic
    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
