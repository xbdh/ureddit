package com.jicyu.ureddit.api.vo.resp.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostGetRespVO {
    String id;
    String title;
    String content;
    String authorId;
    String subredditId;
    String authorName;
    String currentVote;

    Long votesAmt;
    Long commentsAmt;

    public LocalDateTime createTime;
}
