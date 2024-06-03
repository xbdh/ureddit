package com.jicyu.ureddit.api.vo.req.subreddit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class SubredditGetByNameReqVO {
    public String name;
    public String loginUserId;
}
