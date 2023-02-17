package com.mahilamandal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.utils.BaseRequest;
import com.mahilamandal.utils.BaseResponse;
import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.PrintType;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    ObjectMapper mapper;
    public MainController() {
        mapper = new ObjectMapper();
    }

    @PostMapping("/processRequest/")
    private String PostData(@RequestBody String data) throws JsonProcessingException {
        BaseResponse response = new BaseResponse();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        BaseRequest request = mapper.readValue(data, BaseRequest.class);

        if (request != null) {
            Logger.printMessage(data,PrintType.Request,request.getRequestType());

            response.setUserId(request.getUserId());
            response.setRequestType(request.getRequestType());

            if (request.getRequestType() != RequestType.None) {
                return callServices(request, data);
            } else {
                response.setMessage("Invalid RequestType : " + request.getRequestType());
            }
        } else {
            response.setMessage("Request object is null");
        }
        return ObjectToString(response);
    }

    private String callServices(BaseRequest baseRequest,String requestData) throws JsonProcessingException {
        Object response=callServiceBasedOnRequestType(baseRequest.getRequestType(),requestData);

        BaseResponse res=(BaseResponse)response;
        res.setUserId(baseRequest.getUserId());
        res.setRequestType(baseRequest.getRequestType());

        String finalResponse=ObjectToString(response);
        Logger.printMessage(finalResponse,PrintType.Response,baseRequest.getRequestType());
        return finalResponse;
    }

    private  String ObjectToString(Object object) throws JsonProcessingException {
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;
    }

    @Autowired()
    private UserRegistrationService userRegistrationService;

    private Object callServiceBasedOnRequestType(RequestType requestType,String requestData) throws JsonProcessingException {
        Object response=new BaseResponse();
        switch(requestType){
            case Test:
                response=new BaseResponse("Test API is working ", StatusCode.Success.ordinal());
                break;
            case UserLogin:

                break;
            case UserRegister:
                UserRegistrationRequest userRegistrationRequest=mapper.readValue(requestData,UserRegistrationRequest.class);
                response= userRegistrationService.addUser(userRegistrationRequest);
                break;
        }
        return response;
    }


}
