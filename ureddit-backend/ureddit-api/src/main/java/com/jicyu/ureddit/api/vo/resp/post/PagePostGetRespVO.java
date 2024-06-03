package com.jicyu.ureddit.api.vo.resp.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PagePostGetRespVO {
    String id;
    String title;
    String content;
    String authorId;
    String subredditId;
    String subredditName;
    String authorName;
    String currentVote;
//    Long upVoteCount;
//    Long downVoteCount;
    Long votesAmt;
    Long commentsAmt;
    public LocalDateTime createTime;
}
