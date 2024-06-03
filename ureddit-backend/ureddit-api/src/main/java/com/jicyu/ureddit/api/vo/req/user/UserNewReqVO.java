package com.jicyu.ureddit.api.vo.req.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNewReqVO {
    public String id;
    public String userId;
    public String username;
    public String image;
}
