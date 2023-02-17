package com.mahilamandal.servicesimplementation;

import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.UserRegistrationResponse;
import com.mahilamandal.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationServiceImplementation implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public UserRegistrationResponse addUser(UserRegistrationRequest userRegistration) {
        return new UserRegistrationResponse(userRegistration.getUserName(),userRegistration.getUserPassword());
    }
}
