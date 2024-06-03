package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.comment.CommentGetByPostIdReqVO;
import com.jicyu.ureddit.api.vo.req.comment.CommentNewReqVO;
import com.jicyu.ureddit.api.vo.resp.comment.CommentGetByPostIdRespVO;
import com.jicyu.ureddit.api.vo.resp.comment.CommentNewRespVO;

import java.util.List;

public interface ICommentApiService {
    CommentNewRespVO insertComment(CommentNewReqVO commentNewReqVO);
    List<CommentGetByPostIdRespVO> getCommentByPostId(CommentGetByPostIdReqVO commentGetByPostIdReqVO);
}
