package com.jicyu.ureddit.api.vo.req.commentvote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVoteNewReqVO {
    public String userId;
    public String commentId;
    public String voteType;
}
