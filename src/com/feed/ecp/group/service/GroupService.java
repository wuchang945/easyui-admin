package com.feed.ecp.group.service;

import com.feed.ecp.common.model.Group;

public interface GroupService {
    int deleteByPrimaryKey(String code);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(Group record);

}
