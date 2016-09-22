package com.wladek.accomodation.service.accomodation;

import com.wladek.accomodation.domain.Role;

import java.util.List;

/**
 * Created by wladek on 11/24/15.
 */
public interface RoleService {
    public Role create(Role role);
    public Role getOne(Long roleId);
    public void delete(Long roleId);
    public List<Role> findAll();
}
