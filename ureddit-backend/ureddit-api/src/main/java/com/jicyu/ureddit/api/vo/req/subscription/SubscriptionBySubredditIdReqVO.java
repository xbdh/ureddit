package com.jicyu.ureddit.api.vo.req.subscription;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionBySubredditIdReqVO {
    String subredditId;
}
