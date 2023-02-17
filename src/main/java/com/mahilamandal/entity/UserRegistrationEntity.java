package com.mahilamandal.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tbl_user_registration",
    uniqueConstraints ={
    @UniqueConstraint(name = "unique_mobileNo",columnNames ="mobileNo"),
//    @UniqueConstraint(name = "unique_userName",columnNames ="userName")
    }
)
public class UserRegistrationEntity {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="user_sequence"
    )
    private int id;
    private int addedBy;
    @Column(nullable = false)
    private String userName;
    private String password;
    @Column(nullable = false)
    private String mobileNo;
    private Date dateTime;
    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId",referencedColumnName = "id")
    private RoleEntity role;
}
