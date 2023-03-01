package com.mahilamandal.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseRequest {
    protected int userId;
    protected int requestType=RequestType.None.ordinal();
    protected  String version;
    protected List<Attribute> attributes=null;
}
