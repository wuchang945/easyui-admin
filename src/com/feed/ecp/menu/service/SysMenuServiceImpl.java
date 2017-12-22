package com.feed.ecp.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feed.ecp.common.model.SysMenu;
import com.feed.ecp.common.model.Users;
import com.feed.ecp.menu.dao.SysMenuDao;
@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysMenuDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SysMenu record) {
		// TODO Auto-generated method stub
		return sysMenuDao.insertSelective(record);
	}

	@Override
	public SysMenu selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sysMenuDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysMenu record) {
		// TODO Auto-generated method stub
		return sysMenuDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysMenu> selectMenuByGroup(Users users) {
		// TODO Auto-generated method stub
		return sysMenuDao.selectMenuByGroup(users);
	}

	@Override
	public List<SysMenu> selectButtonByGroup(String usergroupcode,
			Integer parentid) {
		// TODO Auto-generated method stub
		return sysMenuDao.selectButtonByGroup(usergroupcode, parentid);
	}

}
