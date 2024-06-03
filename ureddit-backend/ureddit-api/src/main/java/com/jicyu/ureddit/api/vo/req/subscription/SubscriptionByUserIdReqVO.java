package com.jicyu.ureddit.api.vo.req.subscription;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionByUserIdReqVO {
    String userId;
}
