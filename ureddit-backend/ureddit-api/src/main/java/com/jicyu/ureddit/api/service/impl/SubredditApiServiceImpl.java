package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.ISubredditRpcClient;
import com.jicyu.ureddit.api.rpcclient.ISubscriptionRpcClient;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameOnlyReqVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameOnlyRespVO;
import com.jicyu.ureddit.dto.subreddit.SubredditDTO;
import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import com.jicyu.ureddit.interfaces.subreddit.ISubredditRpc;
import com.jicyu.ureddit.api.service.ISubredditApiService;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditNewReqVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameRespVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditNewRespVO;
import com.jicyu.ureddit.interfaces.subscription.ISubscriptionRpc;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubredditApiServiceImpl implements ISubredditApiService {

    private ISubredditRpcClient subredditRpc;

    private ISubscriptionRpcClient subscriptionRpc;
    @Autowired
    public SubredditApiServiceImpl(ISubredditRpcClient subredditRpc, ISubscriptionRpcClient subscriptionRpc) {
        this.subredditRpc = subredditRpc;
        this.subscriptionRpc = subscriptionRpc;
    }

    @Override
    public SubredditNewRespVO createSubreddit(SubredditNewReqVO subredditNewReqVO) {
        SubredditDTO subredditDTO = new SubredditDTO();
        subredditDTO.setName(subredditNewReqVO.getName());
        subredditDTO.setCreatorId(subredditNewReqVO.getCreatorId());

        String subredditId = subredditRpc.insertSubreddit(subredditDTO);


        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setUserId(subredditNewReqVO.getCreatorId());
        subscriptionDTO.setSubredditId(subredditId);

        subscriptionRpc.insertSubscription(subscriptionDTO);

        SubredditNewRespVO respVO = new SubredditNewRespVO();
        respVO.setId(subredditId);

        return respVO;
    }

    @Override
    public SubredditGetByNameRespVO getSubredditByName(SubredditGetByNameReqVO subredditGetByNameReqVO) {
        SubredditGetByNameRespVO respVO = new SubredditGetByNameRespVO();
        SubredditDTO subredditDTO = subredditRpc.findSubredditByName(subredditGetByNameReqVO.getName());
        boolean subscribed = subscriptionRpc.isSubscribed(subredditGetByNameReqVO.getLoginUserId(), subredditDTO.getId());
        long count = subscriptionRpc.countSubscriptionFollower(subredditDTO.getId());

        respVO.setId(subredditDTO.getId());
        respVO.setName(subredditDTO.getName());
        respVO.setCreatorId(subredditDTO.getCreatorId());
        respVO.setCreateTime(subredditDTO.getCreateTime());
        respVO.setSubscribed(subscribed);
        respVO.setCount(count);

        return respVO;
    }

    @Override
    public SubredditGetByNameOnlyRespVO getSubredditByNameOnly(SubredditGetByNameOnlyReqVO subredditGetByNameOnlyReqVO) {
        SubredditGetByNameOnlyRespVO respVO = new SubredditGetByNameOnlyRespVO();
        SubredditDTO subredditDTO = subredditRpc.findSubredditByName(subredditGetByNameOnlyReqVO.getName());

        respVO.setId(subredditDTO.getId());
        respVO.setName(subredditDTO.getName());
        respVO.setCreatorId(subredditDTO.getCreatorId());
        respVO.setCreateTime(subredditDTO.getCreateTime());

        return respVO;
    }
}
