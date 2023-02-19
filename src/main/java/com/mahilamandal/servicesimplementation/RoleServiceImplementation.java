package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.repository.RoleRepository;
import com.mahilamandal.request.RoleRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.RoleResponse;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseResponse addRole(RoleRequest roleRequest) {
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleName(roleRequest.getRoleName());
        roleRepository.save(roleEntity);
        return new BaseResponse("Role Added Successfully", StatusCode.Success.ordinal());
    }

    @Override
    public Response<RoleResponse> getAllRole() {
        Response<RoleResponse> response=new Response<>();
        RoleResponse roleResponse=new RoleResponse();

        roleResponse.setRoles(roleRepository.findAll());
        response.setResponse(roleResponse);

        response.setMessage("Roles fetched Successfully");
        response.setStatusCode(StatusCode.Success.ordinal());
        return response;
    }


    @Override
    public Response<RoleEntity> getRoleById(int roleId) {

        Response<RoleEntity> response = new Response<>();
        RoleEntity roleEntity = roleRepository.findById(roleId).get();

        response.setResponse(roleEntity);
        return response;
    }
}
