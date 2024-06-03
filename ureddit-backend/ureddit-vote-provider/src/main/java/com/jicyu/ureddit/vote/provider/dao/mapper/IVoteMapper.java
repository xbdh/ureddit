package com.jicyu.ureddit.vote.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.vote.provider.dao.po.VotePO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IVoteMapper extends BaseMapper<VotePO> {
}
