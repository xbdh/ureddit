package com.jicyu.ureddit.api.service.impl;

import com.jicyu.ureddit.api.rpcclient.*;
import com.jicyu.ureddit.api.service.IPostApiService;
import com.jicyu.ureddit.api.vo.req.post.*;
import com.jicyu.ureddit.api.vo.resp.post.PagePostGetRespVO;
import com.jicyu.ureddit.api.vo.resp.post.PostGetRespVO;
import com.jicyu.ureddit.api.vo.resp.post.PostNewRespVO;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.post.PostDTO;
import com.jicyu.ureddit.dto.subreddit.SubredditDTO;
import com.jicyu.ureddit.dto.subscription.SubscriptionDTO;
import com.jicyu.ureddit.dto.user.UserDTO;
import com.jicyu.ureddit.dto.vote.VoteDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class PostApiServiceImpl  implements IPostApiService {

    private IPostRpcClient postRpc;
    private ISubredditRpcClient subredditRpc;
    private IUserRpcClient userRpc;
    private IVoteRpcClient voteRpc;
    private ISubscriptionRpcClient subscriptionRpc;
    private ICommentRpcClient commentRpc;
    @Autowired
    public void setPostRpc(IPostRpcClient postRpc) {
        this.postRpc = postRpc;
    }
    @Autowired
    public void setSubredditRpc(ISubredditRpcClient subredditRpc) {
        this.subredditRpc = subredditRpc;
    }
    @Autowired
    public void setUserRpc(IUserRpcClient userRpc) {
        this.userRpc = userRpc;
    }
    @Autowired
    public void setVoteRpc(IVoteRpcClient voteRpc) {
        this.voteRpc = voteRpc;
    }
    @Autowired
    public void setSubscriptionRpc(ISubscriptionRpcClient subscriptionRpc) {
        this.subscriptionRpc = subscriptionRpc;
    }

    @Autowired
public void setCommentRpc(ICommentRpcClient commentRpc) {
        this.commentRpc = commentRpc;
    }
    @Override
    public PostNewRespVO insertPost(PostNewReqVO postNewReqVO) {
        PostDTO postDTO = new PostDTO();
        postDTO.setAuthorId(postNewReqVO.getAuthorId());
        postDTO.setSubredditId(postNewReqVO.getSubredditId());
        postDTO.setTitle(postNewReqVO.getTitle());
        postDTO.setContent(postNewReqVO.getContent());

        String postId = postRpc.createPost(postDTO);


        PostNewRespVO respVO = new PostNewRespVO();
        respVO.setId(postId);
        return respVO;
    }

    @Override
    public PostGetRespVO findPostById(PostGetByIdReqVO postGetByIdReqVO) {

        PostDTO postDTO = postRpc.findPostById(postGetByIdReqVO.getId());
        PostGetRespVO respVO = ConvertBeanUtils.convert(postDTO, PostGetRespVO.class);
        UserDTO userDTO = userRpc.findUserByUserId(postDTO.getAuthorId());
        VoteDTO voteDTO = voteRpc.getCurrentVote(
                 postGetByIdReqVO.getId()
                ,postGetByIdReqVO.getLoginUserId());

        if(voteDTO!=null){
            respVO.setCurrentVote(voteDTO.getVoteType());
        }else {
            respVO.setCurrentVote("*");
        }
        Long upCount = voteRpc.getVoteCount(postDTO.getId(),"+");
        Long downCount = voteRpc.getVoteCount(postDTO.getId(),"-");
        Long totalCount = upCount-downCount;

        respVO.setVotesAmt(totalCount);
        //System.out.println(userDTO);
        respVO.setAuthorName(userDTO.getUsername());

        return respVO;
    }

    @Override
    public List<PostGetRespVO> findPostsByAuthorId(PostGetByAuthorIdReqVO postGetByAuthorIdReqVO) {
        List<PostDTO> ls = postRpc.findPostsByAuthorId(postGetByAuthorIdReqVO.getAuthorId());
        List<PostGetRespVO> respVOs = ConvertBeanUtils.convertList(ls, PostGetRespVO.class);
        return respVOs;

    }

    @Override
    public List<PostGetRespVO> findPostsBySubredditId(PostGetBySubredditIdReqVO postGetBySubredditIdReqVO) {
        List<PostDTO> ls = postRpc.findPostsBySubredditId(postGetBySubredditIdReqVO.getSubredditId());
        List<PostGetRespVO> respVOs = ConvertBeanUtils.convertList(ls, PostGetRespVO.class);
        return respVOs;
    }

    @Override
    public List<PagePostGetRespVO> findPagePostsByAuthorId(PagePostGetByAuthorIdReqVO pagePostGetByAuthorIdReqVO) {
        List<PostDTO> ls= postRpc.findPagePostsByAuthorId(
                pagePostGetByAuthorIdReqVO.getPageNum(),
                pagePostGetByAuthorIdReqVO.getSize(),
                pagePostGetByAuthorIdReqVO.getAuthorId());
        List<PagePostGetRespVO> respVOs = ConvertBeanUtils.convertList(ls, PagePostGetRespVO.class);
        return respVOs;
    }

    @Override
    public List<PagePostGetRespVO> findPagePostsBySubredditId(PagePostGetBySubredditIdReqVO pagePostGetBySubredditIdReqVO) {
        List<PostDTO> ls= postRpc.findPagePostsBySubredditId(
                pagePostGetBySubredditIdReqVO.getPageNum(),
                pagePostGetBySubredditIdReqVO.getSize(),
                pagePostGetBySubredditIdReqVO.getSubredditId());
        List<PagePostGetRespVO> respVOs = ConvertBeanUtils.convertList(ls, PagePostGetRespVO.class);
        return respVOs;
    }

    @Override
    public List<PagePostGetRespVO> findPagePostsByAuthorName(PagePostGetByAuthorNameReqVO pagePostGetByAuthorNameReqVO) {
            UserDTO userDTO = userRpc.findUserByUsername(pagePostGetByAuthorNameReqVO.getAuthorName());

            List<PostDTO> ls= postRpc.findPagePostsByAuthorId(
                    pagePostGetByAuthorNameReqVO.getPageNum(),
                    pagePostGetByAuthorNameReqVO.getSize(),
                    userDTO.getId());

            List<PagePostGetRespVO> respVOs =new ArrayList<>();
            for (PostDTO postDTO : ls) {
                PagePostGetRespVO respVO = ConvertBeanUtils.convert(postDTO, PagePostGetRespVO.class);

                respVO.setAuthorName(pagePostGetByAuthorNameReqVO.getAuthorName());
                SubredditDTO subredditDTO = subredditRpc.findSubredditById(postDTO.getSubredditId());
                respVO.setSubredditName(subredditDTO.getName());
                respVOs.add(respVO);
            }
            return respVOs;

    }

    @Override
    public List<PagePostGetRespVO> findPagePostsBySubredditName(PagePostGetBySubredditNameReqVO ReqVO) {
        List<PagePostGetRespVO> respVOs =new ArrayList<>();

        // 查看子版块的帖子
        if (ReqVO.getSubredditName()!=null&&ReqVO.getLoginUserId()!=null){
            SubredditDTO subredditDTO = subredditRpc.findSubredditByName(ReqVO.getSubredditName());

            List<PostDTO> ls= postRpc.findPagePostsBySubredditId(
                    ReqVO.getPageNum(),
                    ReqVO.getSize(),
                    subredditDTO.getId());


            for (PostDTO postDTO : ls) {
                PagePostGetRespVO respVO = ConvertBeanUtils.convert(postDTO, PagePostGetRespVO.class);

                respVO.setSubredditName(ReqVO.getSubredditName());
                Long commentsAtm = commentRpc.countCommentsByPostId(postDTO.getId());
                respVO.setCommentsAmt(commentsAtm);
                UserDTO userDTO = userRpc.findUserByUserId(postDTO.getAuthorId());
                VoteDTO voteDTO = voteRpc.getCurrentVote(postDTO.getId(),ReqVO.getLoginUserId());
                if(voteDTO!=null){
                    respVO.setCurrentVote(voteDTO.getVoteType());
                }else {
                    respVO.setCurrentVote("*");
                }
                Long upCount = voteRpc.getVoteCount(postDTO.getId(),"+");
                Long downCount = voteRpc.getVoteCount(postDTO.getId(),"-");
                Long totalCount = upCount-downCount;

                respVO.setVotesAmt(totalCount);
                //System.out.println(userDTO);
                respVO.setAuthorName(userDTO.getUsername());
                respVOs.add(respVO);
            }
        }
        // 查看登录用户主页的帖子
        if (ReqVO.getSubredditName()==null&&ReqVO.getLoginUserId()!=null) {
            List<SubscriptionDTO> ls = subscriptionRpc.findSubscriptionByUserId(ReqVO.getLoginUserId());
            List<String> subredditIds = new ArrayList<>();
            for (SubscriptionDTO subscriptionDTO : ls) {
                subredditIds.add(subscriptionDTO.getSubredditId());
            }
            if (subredditIds.isEmpty()){
                return respVOs;
            }
            List<PostDTO> ls2 = postRpc.findPagePostsInSubredditIds(
                    ReqVO.getPageNum(),
                    ReqVO.getSize(),
                    subredditIds);

            for (PostDTO postDTO : ls2) {
                PagePostGetRespVO respVO = ConvertBeanUtils.convert(postDTO, PagePostGetRespVO.class);

                SubredditDTO subredditDTO = subredditRpc.findSubredditById(postDTO.getSubredditId());
                respVO.setSubredditName(subredditDTO.getName());

                Long commentsAtm = commentRpc.countCommentsByPostId(postDTO.getId());
                respVO.setCommentsAmt(commentsAtm);

                UserDTO userDTO = userRpc.findUserByUserId(postDTO.getAuthorId());
                respVO.setAuthorName(userDTO.getUsername());
                VoteDTO voteDTO = voteRpc.getCurrentVote(postDTO.getId(),ReqVO.getLoginUserId());
                if(voteDTO!=null){
                    respVO.setCurrentVote(voteDTO.getVoteType());
                }else {
                    respVO.setCurrentVote("*");
                }
                Long upCount = voteRpc.getVoteCount(postDTO.getId(),"+");
                Long downCount = voteRpc.getVoteCount(postDTO.getId(),"-");
                Long totalCount = upCount-downCount;

                respVO.setVotesAmt(totalCount);
                //System.out.println(userDTO);

                respVOs.add(respVO);
            }
        }

        // 查看未登录用户主页的帖子
        if (ReqVO.getSubredditName()==null&&ReqVO.getLoginUserId()==null) {
            List<PostDTO> ls = postRpc.findPagePosts(
                    ReqVO.getPageNum(),
                    ReqVO.getSize());

            for (PostDTO postDTO : ls) {
                PagePostGetRespVO respVO = ConvertBeanUtils.convert(postDTO, PagePostGetRespVO.class);
                SubredditDTO subredditDTO = subredditRpc.findSubredditById(postDTO.getSubredditId());
                respVO.setSubredditName(subredditDTO.getName());
                Long commentsAtm = commentRpc.countCommentsByPostId(postDTO.getId());
                respVO.setCommentsAmt(commentsAtm);
                UserDTO userDTO = userRpc.findUserByUserId(postDTO.getAuthorId());
                respVO.setAuthorName(userDTO.getUsername());
                respVO.setCurrentVote("*");
                Long upCount = voteRpc.getVoteCount(postDTO.getId(),"+");
                Long downCount = voteRpc.getVoteCount(postDTO.getId(),"-");
                Long totalCount = upCount-downCount;

                respVO.setVotesAmt(totalCount);
                //System.out.println(userDTO);

                respVOs.add(respVO);
            }

        }
        return respVOs;
    }

}
