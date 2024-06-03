package com.jicyu.ureddit.api.vo.resp.subscription;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionByUserIdRespVO {
    List<String> subredditIds;
}
