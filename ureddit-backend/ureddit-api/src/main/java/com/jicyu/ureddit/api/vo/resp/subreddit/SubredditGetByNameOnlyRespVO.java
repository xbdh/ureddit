package com.jicyu.ureddit.api.vo.resp.subreddit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditGetByNameOnlyRespVO {
    public String id;
    public String name;
    public String creatorId;
    public LocalDateTime createTime;
}
