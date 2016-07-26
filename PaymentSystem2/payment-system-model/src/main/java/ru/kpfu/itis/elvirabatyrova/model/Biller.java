package ru.kpfu.itis.elvirabatyrova.model;

import javax.persistence.*;

/**
 * Created by Pc on 26.07.2016.
 */

@Entity
@Table(name = "biller", schema = "public", catalog = "payment_system")
public class Biller {

    private Integer id;
    private String companyName;

    public Biller() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "biller_id_seq")
    @SequenceGenerator(name = "biller_id_seq", sequenceName = "biller_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
