package com.jicyu.ureddit.api.vo.req.subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SubscriptionNewReqVO {
    public String userId;
    public String subredditId;
}
