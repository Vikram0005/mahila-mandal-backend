package com.mahilamandal.response;

import com.mahilamandal.entity.Address;
import com.mahilamandal.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private int id;
    private int addedBy;
    private String userName;
    private String password;
    private String mobileNo;
    private RoleEntity role;
    private Address address;
}
