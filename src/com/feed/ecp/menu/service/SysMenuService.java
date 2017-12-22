package com.feed.ecp.menu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.feed.ecp.common.model.SysMenu;
import com.feed.ecp.common.model.Users;

public interface SysMenuService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);
    
    List<SysMenu> selectMenuByGroup(Users users);
    
    List<SysMenu> selectButtonByGroup(@Param("usergroupcode") String usergroupcode,@Param("parentid") Integer parentid);

}