package com.jicyu.ureddit.subscription.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.subscription.provider.dao.po.SubscriptionPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISubscriptionMapper extends BaseMapper<SubscriptionPO> {
}
