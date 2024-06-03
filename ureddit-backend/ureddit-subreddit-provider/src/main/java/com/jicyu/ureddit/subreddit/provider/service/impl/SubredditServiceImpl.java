package com.jicyu.ureddit.subreddit.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.dto.subreddit.SubredditDTO;
import com.jicyu.ureddit.subreddit.provider.dao.mapper.ISubredditMapper;
import com.jicyu.ureddit.subreddit.provider.dao.po.SubredditPO;
import com.jicyu.ureddit.subreddit.provider.service.ISubredditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
@Service
@Slf4j
public class SubredditServiceImpl implements ISubredditService {
    @Autowired
    private ISubredditMapper subredditMapper;
    @Override
    public String insertSubreddit(SubredditDTO subredditDTO) {
        SubredditPO subredditPO =ConvertBeanUtils.convert(subredditDTO, SubredditPO.class);
        subredditMapper.insert(subredditPO);
        String id=subredditPO.getId();
        //log.info("insertSubreddit resp: {}",id);
        return id;
    }

    @Override
    public SubredditDTO findSubredditByName(String name) {
        LambdaQueryWrapper<SubredditPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubredditPO::getName,name);
        SubredditPO subredditPO = subredditMapper.selectOne(wrapper);
//        log.info("findSubredditBymaName resp: {}",subredditPO);
//        log.info("findSubredditBymaName resp: {}",ConvertBeanUtils.convert(subredditPO, SubredditDTO.class));
        return ConvertBeanUtils.convert(subredditPO, SubredditDTO.class);
    }

    @Override
    public SubredditDTO findSubredditById(String id) {
        LambdaQueryWrapper<SubredditPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SubredditPO::getId,id);
        SubredditPO subredditPO = subredditMapper.selectOne(wrapper);
        return ConvertBeanUtils.convert(subredditPO, SubredditDTO.class);
    }
}
