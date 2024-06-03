package com.jicyu.ureddit.api.vo.req.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagePostGetByAuthorIdReqVO {
    int pageNum;
    int size;
    String authorId;
}
