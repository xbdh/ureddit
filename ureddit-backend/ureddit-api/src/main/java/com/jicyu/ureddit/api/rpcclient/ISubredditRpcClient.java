package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.subreddit.SubredditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="ureddit-subreddit-provider",path = "subreddit",url = "${rpcClient.subredditUrl}")
public interface ISubredditRpcClient {
    @PostMapping("/insertSubreddit")
    String insertSubreddit(@RequestBody SubredditDTO subredditDTO);


    @GetMapping("/deleteSubreddit")
    SubredditDTO findSubredditByName(@RequestParam("name") String name);

    @GetMapping("/findSubredditById")
    SubredditDTO findSubredditById(@RequestParam("id") String id) ;

}
