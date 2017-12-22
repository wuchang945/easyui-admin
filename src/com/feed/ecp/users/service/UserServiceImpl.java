package com.feed.ecp.users.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feed.ecp.common.constants.Constants;
import com.feed.ecp.common.model.Users;
import com.feed.ecp.common.modelDTO.ResultDto;
import com.feed.ecp.common.modelDTO.UsersDto;
import com.feed.ecp.users.dao.UsersDao;
import com.feed.util.DES;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersDao userDao;

	@Override
	public Users userLogin(Users user) {
		// TODO Auto-generated method stub
		return userDao.userLogin(user);
	}

	@Override
	public List<Users> getAllSysUsers(UsersDto usersDto) {
		// TODO Auto-generated method stub
		return userDao.getAllSysUsers(usersDto);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Users record) {
		// TODO Auto-generated method stub
		return userDao.insertSelective(record);
	}

	@Override
	public Users selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Users record) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int getAllSysUsersCount(UsersDto usersDto) {
		// TODO Auto-generated method stub
		return userDao.getAllSysUsersCount(usersDto);
	}

}
