package com.mahilamandal.controller;

import com.mahilamandal.request.GroupRequest;
import com.mahilamandal.request.RoleRequest;
import com.mahilamandal.request.UserLoginRequest;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.request.dataclasses.*;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.services.GroupService;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;

@Component
public class Manager {
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GroupService groupService;

    public Object callServicesBasedOnRequestType(int requestType, String requestData){
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
        return MainController.getGson().fromJson(data,(Type)requestOfR);
    }
}
