package com.mahilamandal.response;

import com.mahilamandal.entity.GroupEntity;
import lombok.Data;
import java.util.List;

@Data
public class GroupResponse {
    private List<GroupEntity> groups;
}
