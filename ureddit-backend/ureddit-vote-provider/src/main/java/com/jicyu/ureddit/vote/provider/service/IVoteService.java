package com.jicyu.ureddit.vote.provider.service;

import com.jicyu.ureddit.dto.vote.VoteDTO;

public interface IVoteService {
    Boolean createVote(VoteDTO voteDTO);

    Boolean deleteVote(VoteDTO voteDTO);
    Long getVoteCount(String postId, String voteType);

    VoteDTO getCurrentVote(String postId, String userId);

    Boolean updateVote(VoteDTO voteDTO);

}
