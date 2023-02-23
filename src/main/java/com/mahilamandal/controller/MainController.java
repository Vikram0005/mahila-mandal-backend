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
    private final Gson gson;
    public MainController() {

        gson=new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
    }

    @PostMapping("/processRequest/")
    private String PostData(@RequestBody String data){
        BaseResponse response = new BaseResponse();

        BaseRequest request=JsonToObject(data,BaseRequest.class);
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

    private String callServices(BaseRequest baseRequest,String requestData) {
        Object response= callServicesBasedOnRequestType(baseRequest.getRequestType(),requestData);

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

    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GroupService groupService;

    private Object callServicesBasedOnRequestType(int requestType, String requestData){
        Object response=new BaseResponse();
        switch(RequestType.values()[requestType]){
            case Test:
                response=new BaseResponse("Test API is working ", StatusCode.Success.ordinal());
                break;
            case AddRole:
                Request<RoleRequest> roleRequest= JsonToObject(requestData, RoleRequestData.class);
                response=roleService.addRole(roleRequest.getRequest());
                break;
            case FetchRole:
                response=roleService.getAllRole();
                break;
            case UserLogin:
                Request<UserLoginRequest> userLoginRequest= JsonToObject(requestData, UserLoginRequestData.class);
                response=userRegistrationService.loginUser(userLoginRequest.getRequest());
                break;
            case UserRegister:
                Request<UserRegistrationRequest> userRegistrationRequest= JsonToObject(requestData, UserRegistrationRequestData.class);
                response= userRegistrationService.addUser(userRegistrationRequest.getRequest());
                break;
            case FindUserById:
                Request request=JsonToObject(requestData,Request.class);
                response=userRegistrationService.findUserById(request.getUserId());
                break;
            case AddGroup:
                Request<GroupRequest> groupRequest=JsonToObject(requestData, GroupRequestData.class);
                response=groupService.addGroup(groupRequest.getRequest());
                break;
            case GetAllGroup:
                response=groupService.getAllGroup();
                break;
            case  GetGroupById:
                Request request1=JsonToObject(requestData,Request.class);
                response=groupService.getGroupById(request1.getUserId());
                break;
        }
        return response;
    }

    private <R> Request JsonToObject(String data, Class<R> requestOfR) {
        return gson.fromJson(data,(Type)requestOfR);
    }
}
