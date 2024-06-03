package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.subscription.*;
import com.jicyu.ureddit.api.vo.resp.subscription.*;

public interface ISubscriptionApiService {
    public SubscriptionNewRespVO subscribe(SubscriptionNewReqVO subscriptionNewReqVO);
    public SubscriptionDeleteRespVO unsubscribe(SubscriptionDeleteReqVO subscriptionDeleteReqVO);
    public SubscriptionCountRespVO countSubscriber(SubscriptionCountReqVO subscriptionCountReqVO);
    public SubscriptionByUserIdRespVO getSubscriptionsByUserId(SubscriptionByUserIdReqVO subscriptionByUserIdReqVO);
    public SubscriptionBySubredditIdRespVO getSubscriptionsBySubredditId(SubscriptionBySubredditIdReqVO subscriptionBySubredditIdReqVO);
}
