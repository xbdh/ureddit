package com.jicyu.ureddit.subscription.provider.service;

import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;


import java.util.List;

public interface ISubscriptionService {

    public String insertSubscription(SubscriptionDTO subscriptionDTO);
    public List<SubscriptionDTO> findSubscriptionByUserId(String userId);
    public List<SubscriptionDTO> findSubscriptionBySubredditId(String subredditId);

    public Long countSubscriptionFollower(String subredditId);

    public boolean isSubscribed(String userId,String subredditId);

    public boolean deleteSubscription(String userId,String subredditId);
}
