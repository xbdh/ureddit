package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.commentvote.CommentVoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.commentvote.CommentVoteNewRespVO;


public interface ICommentVoteApiService {
   public CommentVoteNewRespVO createCommentVote(CommentVoteNewReqVO reqVO);

}
