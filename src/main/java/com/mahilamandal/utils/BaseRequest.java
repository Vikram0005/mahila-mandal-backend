package com.mahilamandal.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseRequest {
    protected int userId;
    protected RequestType requestType=RequestType.None;
}
