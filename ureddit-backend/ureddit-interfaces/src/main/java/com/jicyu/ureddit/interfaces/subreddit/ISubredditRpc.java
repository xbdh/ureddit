package com.jicyu.ureddit.interfaces.subreddit;

import com.jicyu.ureddit.dto.subreddit.SubredditDTO;

public interface ISubredditRpc {
    public String insertSubreddit(SubredditDTO subredditDTO);
    public SubredditDTO findSubredditByName(String name);
    public SubredditDTO findSubredditById(String id);

}
