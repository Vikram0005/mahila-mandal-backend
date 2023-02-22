package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.UserLoginRequest;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.UserInfo;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRegistrationServiceImplementation implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public BaseResponse addUser(UserRegistrationRequest userRegistration) {

        BaseResponse baseResponse=new BaseResponse();

        UserRegistrationEntity entity=new UserRegistrationEntity();
        entity.setUserName(userRegistration.getUserName());
        entity.setAddedBy(userRegistration.getAddedBy());
        entity.setPassword(userRegistration.getPassword());
        entity.setMobileNo(userRegistration.getMobileNo());
        entity.setAddress(userRegistration.getAddress());

        RoleEntity roleEntity=roleService.getRoleById(userRegistration.getRoleId());
        if (roleEntity!=null){
            entity.setRole(roleEntity);
            userRegistrationRepository.save(entity);
            baseResponse.setMessage("Data Inserted Successfully");
            baseResponse.setStatusCode(StatusCode.Success.ordinal());
        }
        else {
            baseResponse.setMessage("Unable to find the role");
            baseResponse.setStatusCode(StatusCode.Failed.ordinal());
        }
        return baseResponse;
    }

    @Override
    public Response<UserInfo> loginUser(UserLoginRequest userLoginRequest) {
        Response<UserInfo> response=new Response<>();

        UserRegistrationEntity entity= userRegistrationRepository.findByMobileNoAndPassword(
                userLoginRequest.getMobileNo(),
                userLoginRequest.getPassword()
        );

        if (entity!=null){
            response.setResponse(new UserInfo(
                    entity.getId(),
                    entity.getAddedBy(),
                    entity.getUserName(),
                    entity.getPassword(),
                    entity.getMobileNo(),
                    entity.getRole(),
                    entity.getAddress()
            ));
            response.setMessage("Login Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }else {
            response.setMessage("Invalid Mobile No Or Password !");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }

    @Override
    public Response<UserInfo> findUserById(int userId) {
        Response<UserInfo> response=new Response<>();

        Optional<UserRegistrationEntity> user= userRegistrationRepository.findById(userId);

        if (user.isPresent()){
            UserRegistrationEntity entity=user.get();
            response.setResponse(new UserInfo(
                    entity.getId(),
                    entity.getAddedBy(),
                    entity.getUserName(),
                    entity.getPassword(),
                    entity.getMobileNo(),
                    entity.getRole(),
                    entity.getAddress()
            ));
            response.setMessage("Fetched Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }else {
            response.setMessage("Invalid User Id !");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }
}
