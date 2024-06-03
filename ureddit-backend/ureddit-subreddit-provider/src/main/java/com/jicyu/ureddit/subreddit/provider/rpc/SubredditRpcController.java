package com.jicyu.ureddit.subreddit.provider.rpc;

import com.jicyu.ureddit.dto.subreddit.SubredditDTO;
import com.jicyu.ureddit.interfaces.subreddit.ISubredditRpc;

import com.jicyu.ureddit.subreddit.provider.service.ISubredditService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subreddit")
public class SubredditRpcController{


    private ISubredditService subredditService;

    @Autowired
    public void setSubredditService(ISubredditService subredditService) {
        this.subredditService = subredditService;
    }

    @PostMapping("/insertSubreddit")
    public String insertSubreddit(@RequestBody SubredditDTO subredditDTO) {

       return subredditService.insertSubreddit(subredditDTO);
    }

    @GetMapping("/deleteSubreddit")
    public SubredditDTO findSubredditByName(@RequestParam("name") String name) {
       return subredditService.findSubredditByName(name);

    }

    @GetMapping("/findSubredditById")
    public SubredditDTO findSubredditById(@RequestParam("id") String id) {
        return subredditService.findSubredditById(id);
    }

}
