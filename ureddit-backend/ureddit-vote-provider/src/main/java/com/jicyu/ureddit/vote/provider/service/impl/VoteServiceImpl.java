package com.jicyu.ureddit.vote.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.vote.VoteDTO;
import com.jicyu.ureddit.vote.provider.dao.mapper.IVoteMapper;
import com.jicyu.ureddit.vote.provider.dao.po.VotePO;
import com.jicyu.ureddit.vote.provider.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl  implements IVoteService {
    private IVoteMapper voteMapper;

    @Autowired
    public VoteServiceImpl(IVoteMapper voteMapper) {

        this.voteMapper = voteMapper;
    }

    @Override
    public Boolean createVote(VoteDTO voteDTO) {
        VotePO votePO = ConvertBeanUtils.convert(voteDTO, VotePO.class);
        int inserted = voteMapper.insert(votePO);
        return inserted > 0;
    }

    @Override
    public Boolean deleteVote(VoteDTO voteDTO) {
        VotePO votePO = ConvertBeanUtils.convert(voteDTO, VotePO.class);
        LambdaQueryWrapper<VotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VotePO::getPostId,votePO.getPostId());
        wrapper.eq(VotePO::getUserId,votePO.getUserId());
        int deleted = voteMapper.delete(wrapper);
        return deleted > 0;
    }

    @Override
    public Long getVoteCount(String postId, String voteType) {
        LambdaQueryWrapper<VotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VotePO::getPostId,postId);
        wrapper.eq(VotePO::getVoteType,voteType);
        return voteMapper.selectCount(wrapper);
    }

    @Override
    public VoteDTO getCurrentVote(String postId, String userId) {
        LambdaQueryWrapper<VotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VotePO::getPostId,postId);
        wrapper.eq(VotePO::getUserId,userId);
        VotePO votePO = voteMapper.selectOne(wrapper);
        return ConvertBeanUtils.convert(votePO,VoteDTO.class);
    }

    @Override
    public Boolean updateVote(VoteDTO voteDTO) {
        VotePO votePO = ConvertBeanUtils.convert(voteDTO, VotePO.class);
        LambdaQueryWrapper<VotePO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VotePO::getPostId,votePO.getPostId());
        wrapper.eq(VotePO::getUserId,votePO.getUserId());
        int updated = voteMapper.update(votePO,wrapper);
        return updated > 0;
    }
}
