package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.IVoteRpcClient;
import com.jicyu.ureddit.api.service.IVoteApiService;
import com.jicyu.ureddit.api.vo.req.vote.VoteCountReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteDeleteReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteExistReqVO;
import com.jicyu.ureddit.api.vo.req.vote.VoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteCountRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteDeleteRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteExistRespVO;
import com.jicyu.ureddit.api.vo.resp.vote.VoteNewRespVO;
import com.jicyu.ureddit.dto.vote.VoteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteApiServiceImpl implements IVoteApiService {

    private IVoteRpcClient voteRpc;

    @Autowired
    public VoteApiServiceImpl(IVoteRpcClient voteRpc) {
        this.voteRpc = voteRpc;
    }

    @Override
    public VoteNewRespVO createVote(VoteNewReqVO reqVO) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setPostId(reqVO.getPostId());
        voteDTO.setUserId(reqVO.getUserId());
        voteDTO.setVoteType(reqVO.getVoteType());
        VoteNewRespVO respVO = new VoteNewRespVO();

        VoteDTO currVote= voteRpc.getCurrentVote(reqVO.getPostId(),reqVO.getUserId());
        Boolean exist = (currVote!=null);
        if(exist){
            if (currVote.getVoteType().equals(reqVO.getVoteType())){
                Boolean deleteSuccess= voteRpc.deleteVote(voteDTO);
                respVO.setSuccess(deleteSuccess);
                return respVO;
            }else {
                Boolean updateSuccess= voteRpc.updateVote(voteDTO);
                respVO.setSuccess(updateSuccess);
                return respVO;
            }
        }
        Boolean createSuccess  = voteRpc.createVote(voteDTO);
        respVO.setSuccess(createSuccess);

        return respVO;
    }

    @Override
    public VoteDeleteRespVO deleteVote(VoteDeleteReqVO reqVO) {
        VoteDTO voteDTO = new VoteDTO();
        voteDTO.setPostId(reqVO.getPostId());
        voteDTO.setUserId(reqVO.getUserId());
        Boolean success  = voteRpc.deleteVote(voteDTO);
        VoteDeleteRespVO respVO = new VoteDeleteRespVO();
        respVO.setSuccess(success);
        return respVO;
    }

    @Override
    public VoteCountRespVO countVote(VoteCountReqVO reqVO) {
        String UpVote="+";
        Long upCount = voteRpc.getVoteCount(reqVO.getPostId(),UpVote);
        String DownVote="-";
        Long downCount = voteRpc.getVoteCount(reqVO.getPostId(),DownVote);
        VoteCountRespVO respVO = new VoteCountRespVO();
        Long totalCount = upCount-downCount;
        respVO.setCount(totalCount);
        return respVO;
    }

    @Override
    public VoteExistRespVO exist(VoteExistReqVO reqVO) {

        VoteDTO voteDTO  = voteRpc.getCurrentVote(reqVO.getPostId(),reqVO.getUserId());
        VoteExistRespVO respVO = new VoteExistRespVO();
        if(voteDTO!=null) {
            respVO.setExist(true);
            respVO.setVoteType(voteDTO.getVoteType());
        }else {
            respVO.setExist(false) ;
        }
        return respVO;
    }
}
