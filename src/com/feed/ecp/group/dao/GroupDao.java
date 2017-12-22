package com.feed.ecp.group.dao;

import com.feed.ecp.common.model.Group;

public interface GroupDao {
    int deleteByPrimaryKey(String code);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Group record);

}