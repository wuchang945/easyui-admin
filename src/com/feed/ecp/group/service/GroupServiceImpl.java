package com.feed.ecp.group.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feed.ecp.common.model.Group;
import com.feed.ecp.group.dao.GroupDao;
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	@Override
	public int deleteByPrimaryKey(String code) {
		// TODO Auto-generated method stub
		return groupDao.deleteByPrimaryKey(code);
	}

	@Override
	public int insertSelective(Group record) {
		// TODO Auto-generated method stub
		return groupDao.insertSelective(record);
	}

	@Override
	public Group selectByPrimaryKey(String code) {
		// TODO Auto-generated method stub
		return groupDao.selectByPrimaryKey(code);
	}

	@Override
	public int updateByPrimaryKeySelective(Group record) {
		// TODO Auto-generated method stub
		return groupDao.updateByPrimaryKeySelective(record);
	}

}
