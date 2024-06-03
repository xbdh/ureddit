package com.jicyu.ureddit.api.controller;


import com.jicyu.ureddit.api.service.IPostApiService;
import com.jicyu.ureddit.api.vo.req.post.*;
import com.jicyu.ureddit.api.vo.resp.post.PostNewRespVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
public class PostController {


    private IPostApiService postService;

    @Autowired
    public void setPostService(IPostApiService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    public WebResponseVO createPost(@RequestBody PostNewReqVO reqVO){
//        log.info("createPost req: {}",reqVO);
        PostNewRespVO respVO = postService.insertPost(reqVO);
//        log.info("createPost resp: {}",respVO);
        return WebResponseVO.success(respVO);
    }

    @PostMapping("/findPostById")
    public WebResponseVO findPostById(@RequestBody PostGetByIdReqVO reqVO){
//        log.info("findPostById req: {}",reqVO);
        return WebResponseVO.success(postService.findPostById(reqVO));

    }
    @PostMapping("/findPostsByAuthorId")
    public WebResponseVO findPostsByAuthorId(@RequestBody PostGetByAuthorIdReqVO reqVO){
//        log.info("findPostsByAuthorId req: {}",reqVO);
        return WebResponseVO.success(postService.findPostsByAuthorId(reqVO));
    }

    @PostMapping("/findPostsBySubredditId")
    public WebResponseVO findPostsBySubredditId(@RequestBody PostGetBySubredditIdReqVO reqVO){
//        log.info("findPostsBySubredditId req: {}",reqVO);
        return WebResponseVO.success(postService.findPostsBySubredditId(reqVO));
    }

    @PostMapping("/findPagePostsByAuthorId")
    public WebResponseVO findPagePostsByAuthorId(@RequestBody PagePostGetByAuthorIdReqVO reqVO){
//        log.info("findPagePostsByAuthorId req: {}",reqVO);
        return WebResponseVO.success(postService.findPagePostsByAuthorId(reqVO));
    }

    @PostMapping("/findPagePostsBySubredditId")
    public WebResponseVO findPagePostsBySubredditId(@RequestBody PagePostGetBySubredditIdReqVO reqVO){
//        log.info("findPagePostsBySubredditId req: {}",reqVO);
        return WebResponseVO.success(postService.findPagePostsBySubredditId(reqVO));
    }

    @PostMapping("/findPagePostsByAuthorName")
    public WebResponseVO findPagePostsByAuthorName(@RequestBody PagePostGetByAuthorNameReqVO reqVO){
//        log.info("findPagePostsByAuthorId req: {}",reqVO);
        return WebResponseVO.success(postService.findPagePostsByAuthorName(reqVO));
    }

    @PostMapping("/findPagePostsBySubredditName")
    public WebResponseVO findPagePostsBySubredditName(@RequestBody PagePostGetBySubredditNameReqVO reqVO){
//        log.info("findPagePostsBySubredditId req: {}",reqVO);
        return WebResponseVO.success(postService.findPagePostsBySubredditName(reqVO));
    }


}
