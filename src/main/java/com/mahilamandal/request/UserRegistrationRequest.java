package com.mahilamandal.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahilamandal.utils.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationRequest extends BaseRequest {
    private String userName;

    private String userPassword;

}
