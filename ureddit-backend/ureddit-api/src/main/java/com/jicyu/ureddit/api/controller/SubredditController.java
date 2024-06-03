package com.jicyu.ureddit.api.controller;

import com.jicyu.ureddit.api.service.ISubredditApiService;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameOnlyReqVO;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditGetByNameReqVO;
import com.jicyu.ureddit.api.vo.req.subreddit.SubredditNewReqVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameOnlyRespVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditGetByNameRespVO;
import com.jicyu.ureddit.api.vo.resp.subreddit.SubredditNewRespVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class SubredditController {

   private ISubredditApiService subredditApiService;

   @Autowired
    public void setSubredditApiService(ISubredditApiService subredditApiService) {
        this.subredditApiService = subredditApiService;
    }

    @PostMapping("/findSubredditByName")
    public WebResponseVO findSubredditByName(@RequestBody SubredditGetByNameReqVO reqVO) {
//        log.info("findSubredditByName req: {}",reqVO);
        SubredditGetByNameRespVO respVO = subredditApiService.getSubredditByName(reqVO);
        return WebResponseVO.success(respVO);

    }
    @PostMapping("/findSubredditByNameOnly")
    public WebResponseVO findSubredditByNameOnly(@RequestBody SubredditGetByNameOnlyReqVO reqVO) {
//        log.info("findSubredditByName req: {}",reqVO);
        SubredditGetByNameOnlyRespVO respVO = subredditApiService.getSubredditByNameOnly(reqVO);
        return WebResponseVO.success(respVO);

    }

    @PostMapping("/createSubreddit")
    public WebResponseVO createSubreddit(@RequestBody SubredditNewReqVO reqVO) {
//        log.info("createSubreddit req: {}",reqVO);
        SubredditNewRespVO respVO = subredditApiService.createSubreddit(reqVO);
        return WebResponseVO.success(respVO);
    }
}
