package com.jicyu.ureddit.interfaces.subscription;

import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;

import java.util.List;

public interface ISubscriptionRpc {
   public String insertSubscription(SubscriptionDTO subscriptionDTO);
   public List<SubscriptionDTO> findSubscriptionByUserId(String userId);
   public List<SubscriptionDTO> findSubscriptionBySubredditId(String subredditId);

   public Long countSubscriptionFollower(String subredditId);

   public boolean isSubscribed(String creatorId,String subredditId);

   public boolean deleteSubscription(String userId,String subredditId);
}
