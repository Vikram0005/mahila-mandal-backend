package com.mahilamandal.request;

import lombok.Data;

@Data
public class UserLoginRequest extends BaseRequest {
    private String mobileNo;
    private String password;
}
