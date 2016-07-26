package ru.kpfu.itis.elvirabatyrova.model;

import javax.persistence.*;

/**
 * Created by Pc on 25.07.2016.
 */

@Entity
@Table(name = "customer", schema = "public", catalog = "payment_system")
public class Customer {

    private Integer id;
    private String firstname;
    private String secondname;
    private String dateofbirth;
    private String address;

    public Customer() {
    }

    public Customer(String firstname, String secondname) {
        this.firstname = firstname;
        this.secondname = secondname;
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
    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "secondname", nullable = false)
    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    @Basic
    @Column(name = "dateofbirth", nullable = false)
    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
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
