package com.mahilamandal.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Request<T> extends BaseRequest {
    private T request;
}
