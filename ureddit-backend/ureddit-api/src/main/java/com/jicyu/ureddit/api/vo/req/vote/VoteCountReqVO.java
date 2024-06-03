package com.jicyu.ureddit.api.vo.req.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteCountReqVO {
    public String userId;
    public String PostId;
}
