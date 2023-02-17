package com.mahilamandal.response;

import com.mahilamandal.utils.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationResponse extends BaseResponse {
    private String userName;
    private String userPassword;
}
