package com.jicyu.ureddit.vote.provider.service;

import com.jicyu.ureddit.dto.vote.CommentVoteDTO;

public interface ICommentVoteService {
    Boolean createCommentVote(CommentVoteDTO commentvoteDTO);

    Boolean deleteCommentVote(CommentVoteDTO commentvoteDTO);
    Long getCommentVoteCount(String commentId, String voteType);

    CommentVoteDTO getCurrentCommentVote(String commentId, String userId);

    Boolean updateCommentVote(CommentVoteDTO commentvoteDTO);
}
