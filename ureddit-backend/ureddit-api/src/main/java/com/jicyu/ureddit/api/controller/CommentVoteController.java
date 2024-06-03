package com.jicyu.ureddit.api.controller;

import com.jicyu.ureddit.api.service.ICommentVoteApiService;
import com.jicyu.ureddit.api.vo.req.commentvote.CommentVoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.commentvote.CommentVoteNewRespVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentVoteController {
    private ICommentVoteApiService commentVoteApiService;

    @Autowired
    public CommentVoteController(ICommentVoteApiService commentVoteApiService) {
        this.commentVoteApiService = commentVoteApiService;
    }
    @PostMapping("/createCommentVote")
    public WebResponseVO createCommentVote(@RequestBody CommentVoteNewReqVO reqVO) {
        CommentVoteNewRespVO respVO = commentVoteApiService.createCommentVote(reqVO);
        return WebResponseVO.success(respVO);
    }
}
