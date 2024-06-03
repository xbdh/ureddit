package com.jicyu.ureddit.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {
    String id;
    String postId;
    String userId;
    String content;
    String replyToId;
    public LocalDateTime createTime;
}
