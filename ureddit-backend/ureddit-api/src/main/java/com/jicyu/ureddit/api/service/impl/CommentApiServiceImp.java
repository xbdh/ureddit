package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.ICommentRpcClient;
import com.jicyu.ureddit.api.rpcclient.ICommentVoteRpcClient;
import com.jicyu.ureddit.api.rpcclient.IUserRpcClient;
import com.jicyu.ureddit.api.service.ICommentApiService;
import com.jicyu.ureddit.api.vo.req.comment.CommentGetByPostIdReqVO;
import com.jicyu.ureddit.api.vo.req.comment.CommentNewReqVO;
import com.jicyu.ureddit.api.vo.resp.comment.CommentGetByPostIdRespVO;
import com.jicyu.ureddit.api.vo.resp.comment.CommentNewRespVO;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.comment.CommentDTO;
import com.jicyu.ureddit.dto.user.UserDTO;
import com.jicyu.ureddit.dto.vote.CommentVoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentApiServiceImp implements ICommentApiService {
    private ICommentRpcClient commentRpcClient;
    private IUserRpcClient userRpcClient;
    private ICommentVoteRpcClient commentVoteRpcClient;

    @Autowired
    public void setUserRpcClient(IUserRpcClient userRpcClient) {
        this.userRpcClient = userRpcClient;
    }
    @Autowired
    public void setCommentVoteRpcClient(ICommentVoteRpcClient commentVoteRpcClient) {
        this.commentVoteRpcClient = commentVoteRpcClient;
    }

    @Autowired
    public CommentApiServiceImp(ICommentRpcClient commentRpcClient) {
        this.commentRpcClient = commentRpcClient;
    }
    @Override
    public CommentNewRespVO insertComment(CommentNewReqVO commentNewReqVO) {

        CommentDTO commentDTO = ConvertBeanUtils.convert(commentNewReqVO, CommentDTO.class);
        String id = commentRpcClient.insertComment(commentDTO);
        CommentNewRespVO commentNewRespVO = new CommentNewRespVO();
        commentNewRespVO.setId(id);
        return commentNewRespVO;
    }

    @Override
    public List<CommentGetByPostIdRespVO> getCommentByPostId(CommentGetByPostIdReqVO commentGetByPostIdReqVO) {
        List<CommentDTO> lsm=commentRpcClient.findRootCommentsByPostId(commentGetByPostIdReqVO.getPostId());
        List<CommentGetByPostIdRespVO> lsr=ConvertBeanUtils.convertList(lsm,CommentGetByPostIdRespVO.class);
        lsr.forEach(resp->{
            UserDTO userDTO=userRpcClient.findUserByUserId(resp.getUserId());
            CommentVoteDTO commentVoteDTO=commentVoteRpcClient.getCurrentCommentVote(resp.getId(),commentGetByPostIdReqVO.getLoginUserId());
            if (commentVoteDTO!=null){
                resp.setCurrentCommentVote(commentVoteDTO.getVoteType());
            }else {
                resp.setCurrentCommentVote("*");
            }
            Long upVoteCount=commentVoteRpcClient.getCommentVoteCount(resp.getId(),"+");
            Long downVoteCount=commentVoteRpcClient.getCommentVoteCount(resp.getId(),"-");
            Long voteCount=upVoteCount-downVoteCount;
            resp.setCommentVotesAmt(voteCount);
            resp.setAuthorName(userDTO.getUsername());
            resp.setImage(userDTO.getImage());
            getChildComment(resp,commentGetByPostIdReqVO.getLoginUserId());
        });
        return lsr;

    }


    private void getChildComment(CommentGetByPostIdRespVO cvresp,String loginUserId){
        String id= cvresp.getId();
        String PostId= cvresp.getPostId();
//        if (replyToId==null||replyToId.equals("")){
//            return;
//        }
        List<CommentDTO> lsm=commentRpcClient.findChildrenCommentsByIdWithParentId(PostId,id);
        List<CommentGetByPostIdRespVO> lsr=ConvertBeanUtils.convertList(lsm,CommentGetByPostIdRespVO.class);
        lsr.forEach(resp1->{
            UserDTO userDTO=userRpcClient.findUserByUserId(resp1.getUserId());
            CommentVoteDTO commentVoteDTO=commentVoteRpcClient.getCurrentCommentVote(resp1.getId(),loginUserId);
            if (commentVoteDTO!=null){
                resp1.setCurrentCommentVote(commentVoteDTO.getVoteType());
            }else {
                resp1.setCurrentCommentVote("*");
            }
            Long upVoteCount=commentVoteRpcClient.getCommentVoteCount(resp1.getId(),"+");
            Long downVoteCount=commentVoteRpcClient.getCommentVoteCount(resp1.getId(),"-");
            Long voteCount=upVoteCount-downVoteCount;
            resp1.setCommentVotesAmt(voteCount);
            resp1.setAuthorName(userDTO.getUsername());
            resp1.setImage(userDTO.getImage());
            getChildComment(resp1,loginUserId);
        });
        cvresp.setReplies(lsr);

        if (lsr.size()>0){
            for (CommentGetByPostIdRespVO respVO:lsr){
                getChildComment(respVO,loginUserId);
            }

        }

//
    }
}
