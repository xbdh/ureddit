package com.jicyu.ureddit.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO implements Serializable {

    String id;
    String title;
    String content;
    String authorId;
    String subredditId;
    public LocalDateTime createTime;
}
