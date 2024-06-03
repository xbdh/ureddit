package com.jicyu.ureddit.subscription.provider.rpc;
import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import com.jicyu.ureddit.interfaces.subscription.ISubscriptionRpc;
import com.jicyu.ureddit.subscription.provider.service.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subscription")
public class SubscriptionRpcController {
    private ISubscriptionService subscriptionService;

    @Autowired
    public void setSubscriptionService(ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/insertSubscription")
    public String insertSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.insertSubscription(subscriptionDTO);
    }

    @GetMapping("/findSubscriptionByUserId")
    public List<SubscriptionDTO> findSubscriptionByUserId(@RequestParam("userId") String userId) {
        return subscriptionService.findSubscriptionByUserId(userId);
    }

    @GetMapping("/findSubscriptionBySubredditId")
    public List<SubscriptionDTO> findSubscriptionBySubredditId(@RequestParam("subredditId") String subredditId) {
        return subscriptionService.findSubscriptionBySubredditId(subredditId);
    }

    @GetMapping("/countSubscriptionFollower")
    public Long countSubscriptionFollower(@RequestParam("subredditId") String subredditId) {
        return subscriptionService.countSubscriptionFollower(subredditId);
    }

    @GetMapping("/isSubscribed")
    public boolean isSubscribed(@RequestParam("creatorId") String creatorId, @RequestParam("subredditId") String subredditId) {
        return subscriptionService.isSubscribed(creatorId,subredditId);
    }

    @GetMapping("/deleteSubscription")
    public boolean deleteSubscription(@RequestParam("userId")String userId, @RequestParam("subredditId") String subredditId) {
        return  subscriptionService.deleteSubscription(userId,subredditId);
    }
}
