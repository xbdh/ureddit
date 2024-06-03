package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameOnlyReqVO;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditNewReqVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameOnlyRespVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameRespVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditNewRespVO;

public interface ISubredditApiService {

    SubredditNewRespVO createSubreddit(SubredditNewReqVO subredditNewReqVO);
    SubredditGetByNameRespVO getSubredditByName(SubredditGetByNameReqVO subredditGetByNameReqVO);

    SubredditGetByNameOnlyRespVO getSubredditByNameOnly(SubredditGetByNameOnlyReqVO subredditGetByNameOnlyReqVO);
}
