package com.jicyu.ureddit.api.controller;

import com.jicyu.ureddit.api.vo.req.vote.VoteCountReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteDeleteReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteExistReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteCountRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteDeleteRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteExistRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteNewRespVO;
import com.jicyu.ureddit.common.vo.WebResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jicyu.ureddit.api.service.IVoteApiService;

@RestController
public class VoteController {

    private IVoteApiService voteApiService;
    @Autowired
    public void setVoteApiService(IVoteApiService voteApiService) {
        this.voteApiService = voteApiService;
    }

    @PostMapping("/createVote")
    public WebResponseVO createVote(@RequestBody VoteNewReqVO reqVO) {
        VoteNewRespVO respVO = voteApiService.createVote(reqVO);
        return WebResponseVO.success(respVO);
    }

    @PostMapping("/deleteVote")
    public WebResponseVO deleteVote(@RequestBody VoteDeleteReqVO reqVO) {
        VoteDeleteRespVO respVO = voteApiService.deleteVote(reqVO);
        return WebResponseVO.success(respVO);
    }

    @PostMapping("/countVote")
    public WebResponseVO countVote(@RequestBody VoteCountReqVO reqVO) {
        VoteCountRespVO respVO = voteApiService.countVote(reqVO);
        return WebResponseVO.success(respVO);
    }

    @PostMapping("/voteExist")
    public WebResponseVO exist(@RequestBody VoteExistReqVO reqVO) {
        VoteExistRespVO respVO = voteApiService.exist(reqVO);
        return WebResponseVO.success(respVO);
    }

}
