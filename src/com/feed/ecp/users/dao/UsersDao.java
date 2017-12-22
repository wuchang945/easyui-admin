package com.feed.ecp.users.dao;

import java.util.List;

import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.UsersDto;

public interface UsersDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);
    
    Users userLogin(Users users);
    
    List<Users> getAllSysUsers(UsersDto usersDto);
    
    int getAllSysUsersCount(UsersDto usersDto);

}