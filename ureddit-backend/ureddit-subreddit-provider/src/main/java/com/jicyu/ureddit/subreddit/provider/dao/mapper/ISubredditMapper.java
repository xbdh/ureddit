package com.jicyu.ureddit.subreddit.provider.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.subreddit.provider.dao.po.SubredditPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ISubredditMapper extends BaseMapper<SubredditPO>{

}
