package com.mahilamandal.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahilamandal.entity.Address;
import com.mahilamandal.utils.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationRequest extends BaseRequest {
    private int id;
    private int addedBy;
    private String userName;
    private String password;
    private String mobileNo;
    private int roleId;
    private Address address;
    private Date dateTime;

}
