package com.jicyu.ureddit.api.controller;

import com.jicyu.ureddit.api.service.ISubscriptionApiService;
import com.jicyu.ureddit.api.vo.req.subscription.*;

import com.jicyu.ureddit.api.vo.resp.subscription.*;
import com.jicyu.ureddit.common.vo.WebResponseVO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class SubscriptionController {

    private ISubscriptionApiService subscriptionApiService;

    @Autowired
    public void setSubscriptionApiService(ISubscriptionApiService subscriptionApiService) {
        this.subscriptionApiService = subscriptionApiService;
    }

    @PostMapping("/createSubscription")
    public WebResponseVO createSubscription(@RequestBody SubscriptionNewReqVO reqVO) {
//        log.info("createSubscription req: {}", reqVO);
        SubscriptionNewRespVO  respVO = subscriptionApiService.subscribe(reqVO);
        return WebResponseVO.success(respVO);
    }

    @PostMapping("/findSubscriptionByUserId")
    public WebResponseVO findSubscriptionByUserId(@RequestBody SubscriptionByUserIdReqVO reqVO) {
//        log.info("findSubscriptionByCreatorId req: {}", reqVO);
        SubscriptionByUserIdRespVO respVO = subscriptionApiService.getSubscriptionsByUserId(reqVO);
        return WebResponseVO.success(respVO);
    }
    @PostMapping("/findSubscriptionBySubredditId")
    public WebResponseVO findSubscriptionBySubredditId(@RequestBody SubscriptionBySubredditIdReqVO reqVO) {
//        log.info("findSubscriptionBySubredditId req: {}", reqVO);
        SubscriptionBySubredditIdRespVO respVO = subscriptionApiService.getSubscriptionsBySubredditId(reqVO);

        return WebResponseVO.success(respVO);
    }

    @PostMapping("/countSubscriptionFollower")
    public WebResponseVO countSubscriptionFollower(@RequestBody SubscriptionCountReqVO reqVO) {
//        log.info("countSubscriptionFollower req: {}", reqVO);
        SubscriptionCountRespVO respVO = subscriptionApiService.countSubscriber(reqVO);

        return   WebResponseVO.success(respVO);
    }
//    @PostMapping("/isSubscribed")
//    public boolean isSubscribed(@RequestBody SubscriptionIsSubscribed info){
//        log.info("isSubscribed req: {}",info);
//        return ISubscriptionRpc.isSubscribed(info.getUserId(),info.getSubredditId());
//    }

    @PostMapping("/deleteSubscription")
    public WebResponseVO deleteSubscription(@RequestBody SubscriptionDeleteReqVO reqVO){
//        log.info("deleteSubscription req: {}",reqVO);
        SubscriptionDeleteRespVO respVO = subscriptionApiService.unsubscribe(reqVO);
        return WebResponseVO.success(respVO);
    }

}
