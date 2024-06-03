package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.ICommentVoteRpcClient;
import com.jicyu.ureddit.api.service.ICommentVoteApiService;
import com.jicyu.ureddit.api.vo.req.commentvote.CommentVoteNewReqVO;
import com.jicyu.ureddit.api.vo.resp.commentvote.CommentVoteNewRespVO;
import com.jicyu.ureddit.dto.vote.CommentVoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentVoteApiServiceImpl implements ICommentVoteApiService {
    private ICommentVoteRpcClient commentVoteRpc;

    @Autowired
    public CommentVoteApiServiceImpl(ICommentVoteRpcClient commentVoteRpc) {
        this.commentVoteRpc = commentVoteRpc;
    }
    @Override
    public CommentVoteNewRespVO createCommentVote(CommentVoteNewReqVO reqVO) {

        CommentVoteDTO commentvoteDTO = new CommentVoteDTO();
        commentvoteDTO.setCommentId(reqVO.getCommentId());
        commentvoteDTO.setUserId(reqVO.getUserId());
        commentvoteDTO.setVoteType(reqVO.getVoteType());
        CommentVoteNewRespVO respVO = new CommentVoteNewRespVO();

        CommentVoteDTO currCommentVote= commentVoteRpc.getCurrentCommentVote(reqVO.getCommentId(),reqVO.getUserId());
        Boolean exist = (currCommentVote!=null);
        if(exist){
            if (currCommentVote.getVoteType().equals(reqVO.getVoteType())){
                Boolean deleteSuccess= commentVoteRpc.deleteCommentVote(commentvoteDTO);
                respVO.setSuccess(deleteSuccess);
                return respVO;
            }else {
                Boolean updateSuccess= commentVoteRpc.updateCommentVote(commentvoteDTO);
                respVO.setSuccess(updateSuccess);
                return respVO;
            }
        }
        Boolean createSuccess  = commentVoteRpc.createCommentVote(commentvoteDTO);
        respVO.setSuccess(createSuccess);

        return respVO;
    }
}
