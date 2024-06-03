package com.jicyu.ureddit.api.vo.req.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentGetByPostIdReqVO {
    String postId;
    String LoginUserId;
}
