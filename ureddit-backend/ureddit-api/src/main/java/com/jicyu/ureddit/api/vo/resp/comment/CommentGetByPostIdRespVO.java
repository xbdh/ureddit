package com.jicyu.ureddit.api.vo.resp.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentGetByPostIdRespVO {
    String id;
    String postId;
    String userId;
    String content;
    String replyToId;
    int level;

    String authorName;
    String image;
    String replyToName;

    String currentCommentVote;
    Long commentVotesAmt;

    public LocalDateTime createTime;
    List<CommentGetByPostIdRespVO> replies;
}
