package com.mahilamandal.services;

import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.UserRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserRegistrationService {

    UserRegistrationResponse addUser(UserRegistrationRequest userRegistration);

}
