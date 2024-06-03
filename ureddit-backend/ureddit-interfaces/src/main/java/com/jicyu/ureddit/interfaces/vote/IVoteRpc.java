package com.jicyu.ureddit.interfaces.vote;

import com.jicyu.ureddit.dto.vote.VoteDTO;

public interface IVoteRpc {
    Boolean createVote(VoteDTO voteDTO  );
    Boolean deleteVote(VoteDTO voteDTO);
    Long getVoteCount(String postId, String voteType);

    VoteDTO getCurrentVote(String postId, String userId);

    Boolean updateVote(VoteDTO voteDTO);
}
