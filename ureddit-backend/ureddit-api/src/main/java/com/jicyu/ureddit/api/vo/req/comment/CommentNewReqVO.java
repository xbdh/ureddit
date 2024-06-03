package com.jicyu.ureddit.api.vo.req.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentNewReqVO {
    String id;
    String postId;
    String userId;
    String content;
    String replyToId;
//    int level;
}
