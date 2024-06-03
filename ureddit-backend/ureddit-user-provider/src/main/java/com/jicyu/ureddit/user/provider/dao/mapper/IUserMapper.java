package com.jicyu.ureddit.user.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.user.provider.dao.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper extends BaseMapper<UserPO> {
}
