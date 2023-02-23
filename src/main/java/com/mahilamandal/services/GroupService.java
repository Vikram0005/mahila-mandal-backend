package com.mahilamandal.services;

import com.mahilamandal.entity.GroupEntity;
import com.mahilamandal.request.GroupRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.GroupResponse;
import com.mahilamandal.response.Response;

public interface GroupService {
    BaseResponse addGroup(GroupRequest grouprequest);
    Response<GroupResponse> getAllGroup();
    Response<GroupEntity> getGroupById(int groupId);
}
