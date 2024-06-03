package com.jicyu.ureddit.api.vo.req.subreddit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubredditNewReqVO {
    public String name;
    public String creatorId;
}
