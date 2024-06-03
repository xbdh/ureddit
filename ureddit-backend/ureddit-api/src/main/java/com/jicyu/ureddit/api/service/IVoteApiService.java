package com.jicyu.ureddit.api.service;

import com.jicyu.ureddit.api.vo.req.vote.VoteCountReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteDeleteReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteExistReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteCountRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteDeleteRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteExistRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteNewRespVO;

public interface IVoteApiService {
    public VoteNewRespVO createVote(VoteNewReqVO reqVO);
    public VoteDeleteRespVO deleteVote(VoteDeleteReqVO reqVO);
    public VoteCountRespVO countVote(VoteCountReqVO reqVO);
    public VoteExistRespVO exist(VoteExistReqVO reqVO);
}
