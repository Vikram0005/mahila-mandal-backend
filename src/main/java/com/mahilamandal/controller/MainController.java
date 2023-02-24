package com.mahilamandal.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mahilamandal.request.*;
import com.mahilamandal.request.dataclasses.*;
import com.mahilamandal.services.GroupService;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.request.BaseRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.PrintType;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
public class MainController {
    private static Gson gson;
    public MainController() {

        gson=new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    public static Gson getGson()
    {
        return gson;
    }

    @PostMapping("/processRequest/")
    private String PostData(@RequestBody String data){
        BaseResponse response = new BaseResponse();

        BaseRequest request=gson.fromJson(data,BaseRequest.class);
        if (request != null) {
            Logger.printMessage(data,PrintType.Request,RequestType.values()[request.getRequestType()]);

            response.setUserId(request.getUserId());
            response.setRequestType(request.getRequestType());

            if (request.getRequestType() != RequestType.None.ordinal()) {
                return callServices(request, data);
            } else {
                response.setMessage("Invalid RequestType : " + request.getRequestType());
            }
        } else {
            response.setMessage("Requested object is null");
        }
        return ConvertObjectToJson(response);
    }

    @Autowired
    private Manager manager;

    private String callServices(BaseRequest baseRequest,String requestData) {
        Object response= manager.callServicesBasedOnRequestType(baseRequest.getRequestType(),requestData);

        BaseResponse res=(BaseResponse)response;
        res.setUserId(baseRequest.getUserId());
        res.setRequestType(baseRequest.getRequestType());

        String finalResponse= ConvertObjectToJson(response);
        Logger.printMessage(finalResponse,PrintType.Response,RequestType.values()[baseRequest.getRequestType()]);
        return finalResponse;
    }

    private  String ConvertObjectToJson(Object object) {
        return  gson.toJson(object);
    }
}