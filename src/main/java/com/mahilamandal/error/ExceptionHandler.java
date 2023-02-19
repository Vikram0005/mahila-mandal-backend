package com.mahilamandal.error;


import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)

    private BaseResponse HandleJsonException(Exception exception){
        BaseResponse response= new BaseResponse();

        Logger.printError(exception.getStackTrace().toString());

        response.setMessage(exception.getMessage());
        response.setStatusCode(StatusCode.InternalServerError.ordinal());
        return response;
    }

}
