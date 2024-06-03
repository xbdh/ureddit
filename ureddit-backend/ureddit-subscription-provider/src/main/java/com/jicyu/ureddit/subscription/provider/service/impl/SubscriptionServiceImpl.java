package com.jicyu.ureddit.subscription.provider.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import com.jicyu.ureddit.subscription.provider.dao.mapper.ISubscriptionMapper;
import com.jicyu.ureddit.subscription.provider.dao.po.SubscriptionPO;
import com.jicyu.ureddit.subscription.provider.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;

import java.util.List;


@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private ISubscriptionMapper subscriptionMapper;

    @Autowired
    public void setSubscriptionMapper(ISubscriptionMapper subscriptionMapper) {
        this.subscriptionMapper = subscriptionMapper;
    }

    @Override
    public String insertSubscription(SubscriptionDTO subscriptionDTO) {
        SubscriptionPO subscriptionPO = ConvertBeanUtils.convert(subscriptionDTO, SubscriptionPO.class);
        subscriptionMapper.insert(subscriptionPO);
        String id = subscriptionPO.getId();
        return id;
    }

    @Override
    public List<SubscriptionDTO> findSubscriptionByUserId(String userId) {
        LambdaQueryWrapper<SubscriptionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubscriptionPO::getUserId,userId);
        List <SubscriptionPO> ls = subscriptionMapper.selectList(wrapper);
        return ConvertBeanUtils.convertList(ls,SubscriptionDTO.class);
    }

    @Override
    public List<SubscriptionDTO> findSubscriptionBySubredditId(String subredditId) {
        LambdaQueryWrapper<SubscriptionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubscriptionPO::getSubredditId,subredditId);
        List <SubscriptionPO> ls = subscriptionMapper.selectList(wrapper);
        return ConvertBeanUtils.convertList(ls,SubscriptionDTO.class);
    }

    @Override
    public Long countSubscriptionFollower(String subredditId) {
        LambdaQueryWrapper<SubscriptionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubscriptionPO::getSubredditId,subredditId);
        Long count = subscriptionMapper.selectCount(wrapper);
        return count;
    }

    @Override
    public boolean isSubscribed(String userId, String subredditId) {
        LambdaQueryWrapper<SubscriptionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubscriptionPO::getUserId, userId);
        wrapper.eq(SubscriptionPO::getSubredditId,subredditId);
        boolean exists = subscriptionMapper.exists(wrapper);
        return exists;
    }

    @Override
    public boolean deleteSubscription(String userId, String subredditId) {
        LambdaQueryWrapper<SubscriptionPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubscriptionPO::getUserId, userId);
        wrapper.eq(SubscriptionPO::getSubredditId,subredditId);
        boolean isDelete =  subscriptionMapper.delete(wrapper)>0;
        return isDelete;
    }
}
