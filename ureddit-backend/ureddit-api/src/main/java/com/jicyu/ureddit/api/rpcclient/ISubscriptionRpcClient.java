package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name ="ureddit-subscription-provider",path = "subscription",url = "${rpcClient.subscriptionUrl}")
public interface ISubscriptionRpcClient {
    @PostMapping("/insertSubscription")
    public String insertSubscription(@RequestBody SubscriptionDTO subscriptionDTO);

    @GetMapping("/findSubscriptionByUserId")
    public List<SubscriptionDTO> findSubscriptionByUserId(@RequestParam("userId") String userId) ;

    @GetMapping("/findSubscriptionBySubredditId")
    public List<SubscriptionDTO> findSubscriptionBySubredditId(@RequestParam("subredditId") String subredditId) ;
    @GetMapping("/countSubscriptionFollower")
    Long countSubscriptionFollower(@RequestParam("subredditId") String subredditId);


    @GetMapping("/isSubscribed")
    boolean isSubscribed(@RequestParam("creatorId") String creatorId, @RequestParam("subredditId") String subredditId) ;


     @GetMapping("/deleteSubscription")
     boolean deleteSubscription(@RequestParam("userId")String userId, @RequestParam("subredditId") String subredditId);

}
