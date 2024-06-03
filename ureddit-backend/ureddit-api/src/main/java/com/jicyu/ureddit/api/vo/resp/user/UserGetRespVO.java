package com.jicyu.ureddit.api.vo.resp.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserGetRespVO {
    public String id;
    public String UserId;
    public String username;
    public String image;
}
