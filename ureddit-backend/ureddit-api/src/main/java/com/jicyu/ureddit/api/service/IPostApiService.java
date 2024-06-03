package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.post.*;
import com.jicyu.ureddit.api.vo.resp.post.PagePostGetRespVO;
import com.jicyu.ureddit.api.vo.resp.post.PostGetRespVO;
import com.jicyu.ureddit.api.vo.resp.post.PostNewRespVO;

import java.util.List;

public interface IPostApiService {
    public PostNewRespVO insertPost(PostNewReqVO postNewReqVO);
    public PostGetRespVO findPostById(PostGetByIdReqVO postGetByIdReqVO);
    public List<PostGetRespVO> findPostsByAuthorId(PostGetByAuthorIdReqVO postGetByAuthorIdReqVO);

    public List<PostGetRespVO> findPostsBySubredditId(PostGetBySubredditIdReqVO postGetBySubredditIdReqVO);

    public List<PagePostGetRespVO> findPagePostsByAuthorId(PagePostGetByAuthorIdReqVO pagePostGetByAuthorIdReqVO);
    public List<PagePostGetRespVO> findPagePostsBySubredditId(PagePostGetBySubredditIdReqVO pagePostGetBySubredditIdReqVO);

    public List<PagePostGetRespVO> findPagePostsByAuthorName(PagePostGetByAuthorNameReqVO pagePostGetByAuthorNameReqVO);
    public List<PagePostGetRespVO> findPagePostsBySubredditName(PagePostGetBySubredditNameReqVO pagePostGetBySubredditNameReqVO);

}

