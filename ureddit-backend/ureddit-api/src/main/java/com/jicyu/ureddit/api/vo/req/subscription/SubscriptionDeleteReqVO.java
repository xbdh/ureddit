package com.jicyu.ureddit.api.vo.req.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscriptionDeleteReqVO {
    public String userId;
    public String subredditId;
}
