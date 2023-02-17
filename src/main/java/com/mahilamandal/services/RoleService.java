package com.mahilamandal.services;

import com.mahilamandal.entity.RoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    RoleEntity getRoleById(int roleId);
}
