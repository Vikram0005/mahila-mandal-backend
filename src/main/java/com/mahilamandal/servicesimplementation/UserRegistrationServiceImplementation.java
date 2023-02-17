package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.RoleRepository;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.UserRegistrationResponse;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.services.UserRegistrationService;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class UserRegistrationServiceImplementation implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private RoleService roleService;

    @Override
    public UserRegistrationResponse addUser(UserRegistrationRequest userRegistration) {

        UserRegistrationEntity entity=new UserRegistrationEntity();
        entity.setUserName(userRegistration.getUserName());
        entity.setAddedBy(userRegistration.getAddedBy());
        entity.setPassword(userRegistration.getPassword());
        entity.setMobileNo(userRegistration.getMobileNo());
        entity.setAddress(userRegistration.getAddress());
        entity.setDateTime(LocalDateTime.now());

        entity.setRole(roleService.getRoleById(userRegistration.getRoleId()));

        userRegistrationRepository.save(entity);
        UserRegistrationResponse userRegistrationResponse=new UserRegistrationResponse();
        userRegistrationResponse.setMessage("Data Inserted Successfully");
        return userRegistrationResponse;
    }
}
