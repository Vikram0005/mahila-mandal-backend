package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.repository.RoleRepository;
import com.mahilamandal.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public RoleEntity getRoleById(int roleId) {
        return roleRepository.findById(roleId).get();
    }
}
