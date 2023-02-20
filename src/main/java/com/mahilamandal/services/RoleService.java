package com.mahilamandal.services;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.request.RoleRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.RoleResponse;
import com.mahilamandal.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    BaseResponse addRole(RoleRequest roleRequest);

    Response<RoleResponse> getAllRole();
    RoleEntity getRoleById(int roleId);
}
