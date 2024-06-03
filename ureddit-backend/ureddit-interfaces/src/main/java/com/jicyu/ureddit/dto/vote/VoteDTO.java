package com.jicyu.ureddit.dto.vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO implements Serializable {
    public String userId;
    public String postId;
    public String voteType;
}
