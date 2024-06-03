package com.jicyu.ureddit.post.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.post.provider.dao.po.PostPO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

@Mapper
public interface IPostMapper extends BaseMapper<PostPO> {
//    public  List<PostPO> findByAuthorId(String authorId);
//    public  List<PostPO> findBySubredditId(String subredditId);
//     public  IPage<PostPO> selectPageBySubredditId(IPage<?> page, String subredditId);
//     public  IPage<PostPO> selectPageByAuthorId(IPage<?> page, String authorId) ;


}
