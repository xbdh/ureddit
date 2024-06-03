package com.jicyu.ureddit.subreddit.provider.service;

import com.jicyu.ureddit.dto.subreddit.SubredditDTO;

public interface ISubredditService {

    SubredditDTO findSubredditByName(String name);

    SubredditDTO findSubredditById(String id);
    String insertSubreddit(SubredditDTO subredditDTO);
}
