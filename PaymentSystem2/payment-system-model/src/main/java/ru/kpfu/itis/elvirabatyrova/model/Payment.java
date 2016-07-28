package ru.kpfu.itis.elvirabatyrova.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Pc on 26.07.2016.
 */

@Entity
@Table(name = "payment", schema = "public", catalog = "payment_system")
public class Payment {

    private Integer id;
    private Customer customer;
    private Biller biller;
    private Long account;
    private double amount;
    private Date paymentDate;

    public Payment() {
    }

    public Payment(Customer customer, Biller biller, Long account, double amount, Date paymentDate) {
        this.customer = customer;
        this.biller = biller;
        this.account = account;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq")
    @SequenceGenerator(name = "payment_id_seq", sequenceName = "payment_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(targetEntity = Biller.class)
    @JoinColumn(name = "biller_id", referencedColumnName = "id")
    public Biller getBiller() {
        return biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
    }

    @Basic
    @Column(name = "account")
    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
