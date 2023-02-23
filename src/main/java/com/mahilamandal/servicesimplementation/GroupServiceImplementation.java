package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.GroupEntity;
import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.GroupRepository;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.GroupRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.GroupResponse;
import com.mahilamandal.response.Response;
import com.mahilamandal.services.GroupService;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupServiceImplementation implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public BaseResponse addGroup(GroupRequest groupRequest) {
        if (groupRequest != null) {

            Optional<UserRegistrationEntity> userRegistrationEntity = userRegistrationRepository.findById(groupRequest.getAddedBy());

            if (userRegistrationEntity.isPresent()) {
                GroupEntity entity = GroupEntity.builder()
                        .groupName(groupRequest.getGroupName())
                        .amount(groupRequest.getAmount())
                        .tenure(groupRequest.getTenure())
                        .user(userRegistrationEntity.get())
                        .build();
                groupRepository.save(entity);
                return new BaseResponse("Group Added Successfully !!",StatusCode.Success.ordinal());
            } else {
                return new BaseResponse("AddedBy value is incorrect!!", StatusCode.Failed.ordinal());
            }
        }
        return new BaseResponse("Invalid data !!",StatusCode.Success.ordinal());
    }

    @Override
    public Response<GroupResponse> getAllGroup() {
        Response<GroupResponse> response=new Response<>();

        GroupResponse groupResponse=new GroupResponse();
        groupResponse.setGroups(groupRepository.findAll());

        response.setResponse(groupResponse);
        response.setMessage("Groups fetched Successfully");
        response.setStatusCode(StatusCode.Success.ordinal());
        return response;
    }

    @Override
    public Response<GroupEntity> getGroupById(int groupId) {
        Response<GroupEntity> response=new Response<>();

        Optional<GroupEntity> groupEntity=groupRepository.findById(groupId);
        if (groupEntity.isPresent()){
            response.setResponse(groupEntity.get());
            response.setMessage("Fetched Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        else {
            response.setMessage("Invalid group id !!");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }
}
