package com.jicyu.ureddit.api.controller;

import com.jicyu.ureddit.api.service.ICommentApiService;
import com.jicyu.ureddit.api.vo.req.comment.CommentGetByPostIdReqVO;
import com.jicyu.ureddit.api.vo.req.comment.CommentNewReqVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    private ICommentApiService commentApiService;

    @Autowired
    public void setCommentApiService(ICommentApiService commentApiService) {
        this.commentApiService = commentApiService;
    }

    @PostMapping("/insertComment")
    public WebResponseVO insertComment(@RequestBody CommentNewReqVO ReqVO) {
        return WebResponseVO.success(commentApiService.insertComment(ReqVO));
    }

    @PostMapping("/findCommentsByPostId")
    public WebResponseVO findCommentsByPostId(@RequestBody CommentGetByPostIdReqVO ReqVO) {
        return WebResponseVO.success(commentApiService.getCommentByPostId(ReqVO));
    }
}
