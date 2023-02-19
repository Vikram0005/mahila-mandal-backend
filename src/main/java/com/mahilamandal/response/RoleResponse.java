package com.mahilamandal.response;

import com.mahilamandal.entity.RoleEntity;
import lombok.Data;

import java.util.List;

@Data
public class RoleResponse{
    private List<RoleEntity> roles;
}
