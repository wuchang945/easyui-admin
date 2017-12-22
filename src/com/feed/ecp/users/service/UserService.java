package com.feed.ecp.users.service;

import java.util.List;

import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.UsersDto;
public interface UserService {
	
	int deleteByPrimaryKey(Integer id);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

   /**
    * 用户登陆
    * @param user
    * @return
    */
   public Users userLogin(Users user);
   
   /**
    * 查询所有的用户信息
    * @param usersDto
    * @return
    */
   public List<Users> getAllSysUsers(UsersDto usersDto);
   
   public int getAllSysUsersCount(UsersDto usersDto);
   
}
