package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.ISubscriptionRpcClient;
import com.jicyu.ureddit.api.service.ISubscriptionApiService;
import com.jicyu.ureddit.api.vo.req.subscription.*;
import com.jicyu.ureddit.api.vo.resp.subscription.*;
import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionApiServiceImpl implements ISubscriptionApiService {

    private ISubscriptionRpcClient subscriptionRpc;
    @Autowired
    public SubscriptionApiServiceImpl(ISubscriptionRpcClient subscriptionRpc) {
        this.subscriptionRpc = subscriptionRpc;
    }

    @Override
    public SubscriptionNewRespVO subscribe(SubscriptionNewReqVO subscriptionNewReqVO) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setUserId(subscriptionNewReqVO.getUserId());
        subscriptionDTO.setSubredditId(subscriptionNewReqVO.getSubredditId());
        boolean isSubscribed =subscriptionRpc.isSubscribed(subscriptionNewReqVO.getUserId(), subscriptionNewReqVO.getSubredditId());

        SubscriptionNewRespVO respVO = new SubscriptionNewRespVO();
        if (isSubscribed){
            return respVO;
        }
        String id = subscriptionRpc.insertSubscription(subscriptionDTO);
        respVO.setId(id);

        return respVO;

    }

    @Override
    public SubscriptionDeleteRespVO unsubscribe(SubscriptionDeleteReqVO subscriptionDeleteReqVO) {
        //要判断是否存在 todo
        boolean isSubscribed =subscriptionRpc.isSubscribed(subscriptionDeleteReqVO.getUserId(), subscriptionDeleteReqVO.getSubredditId());
        SubscriptionDeleteRespVO respVO = new SubscriptionDeleteRespVO();
        if (!isSubscribed){
            return respVO;
        }
        boolean success = subscriptionRpc.deleteSubscription(subscriptionDeleteReqVO.getUserId(), subscriptionDeleteReqVO.getSubredditId());
        respVO.setSuccess(success);

        return respVO;
    }

    @Override
    public SubscriptionCountRespVO countSubscriber(SubscriptionCountReqVO subscriptionCountReqVO) {

        Long count = subscriptionRpc.countSubscriptionFollower(subscriptionCountReqVO.getSubredditId());
        SubscriptionCountRespVO respVO = new SubscriptionCountRespVO();
        respVO.setCount(count);

        return respVO;
    }

    @Override
    public SubscriptionByUserIdRespVO getSubscriptionsByUserId(SubscriptionByUserIdReqVO subscriptionByUserIdReqVO) {
        List<SubscriptionDTO> ls = subscriptionRpc.findSubscriptionByUserId(subscriptionByUserIdReqVO.getUserId());

        SubscriptionByUserIdRespVO respVO = new SubscriptionByUserIdRespVO();
        List<String> subredditIds = new ArrayList<>();
        for (SubscriptionDTO subscriptionDTO : ls) {
            subredditIds.add(subscriptionDTO.getSubredditId());
        }
        respVO.setSubredditIds(subredditIds);
        return respVO;

    }

    @Override
    public SubscriptionBySubredditIdRespVO getSubscriptionsBySubredditId(SubscriptionBySubredditIdReqVO subscriptionBySubredditIdReqVO) {
        List<SubscriptionDTO> ls = subscriptionRpc.findSubscriptionBySubredditId(subscriptionBySubredditIdReqVO.getSubredditId());

        SubscriptionBySubredditIdRespVO respVO = new SubscriptionBySubredditIdRespVO();
        List<String> userIds = new ArrayList<>();
        for (SubscriptionDTO subscriptionDTO : ls) {
            userIds.add(subscriptionDTO.getUserId());
        }
        respVO.setUserIds(userIds);
        return respVO;
    }
}
