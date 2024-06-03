package com.jicyu.ureddit.vote.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.vote.CommentVoteDTO;
import com.jicyu.ureddit.vote.provider.dao.mapper.ICommentVoteMapper;
import com.jicyu.ureddit.vote.provider.dao.po.CommentVotePO;
import com.jicyu.ureddit.vote.provider.dao.po.VotePO;
import com.jicyu.ureddit.vote.provider.service.ICommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class CommentVoteServiceImpl  implements ICommentVoteService {
    private ICommentVoteMapper commentVoteMapper;

    @Autowired
    public CommentVoteServiceImpl(ICommentVoteMapper commentVoteMapper) {
        this.commentVoteMapper = commentVoteMapper;
    }
    @Override
    public Boolean createCommentVote(CommentVoteDTO commentvoteDTO) {
        CommentVotePO commentVotePO = ConvertBeanUtils.convert(commentvoteDTO, CommentVotePO.class);
        return commentVoteMapper.insert(commentVotePO) > 0;
    }

    @Override
    public Boolean deleteCommentVote(CommentVoteDTO commentvoteDTO) {
        CommentVotePO commentVotePO = ConvertBeanUtils.convert(commentvoteDTO, CommentVotePO.class);
        LambdaQueryWrapper<CommentVotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentVotePO::getCommentId,commentVotePO.getCommentId());
        wrapper.eq(CommentVotePO::getUserId,commentVotePO.getUserId());
        return commentVoteMapper.delete(wrapper) > 0;
    }

    @Override
    public Long getCommentVoteCount(String commentId, String voteType) {
        LambdaQueryWrapper<CommentVotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentVotePO::getCommentId,commentId);
        wrapper.eq(CommentVotePO::getVoteType,voteType);
        return commentVoteMapper.selectCount(wrapper);
    }

    @Override
    public CommentVoteDTO getCurrentCommentVote(String commentId, String userId) {
        LambdaQueryWrapper<CommentVotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentVotePO::getCommentId,commentId);
        wrapper.eq(CommentVotePO::getUserId,userId);
        CommentVotePO commentVotePO = commentVoteMapper.selectOne(wrapper);
        return ConvertBeanUtils.convert(commentVotePO,CommentVoteDTO.class);
    }

    @Override
    public Boolean updateCommentVote(CommentVoteDTO commentvoteDTO) {
        CommentVotePO commentVotePO = ConvertBeanUtils.convert(commentvoteDTO, CommentVotePO.class);
        LambdaQueryWrapper<CommentVotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentVotePO::getCommentId,commentVotePO.getCommentId());
        wrapper.eq(CommentVotePO::getUserId,commentVotePO.getUserId());
        return commentVoteMapper.update(commentVotePO,wrapper) > 0;
    }
}
