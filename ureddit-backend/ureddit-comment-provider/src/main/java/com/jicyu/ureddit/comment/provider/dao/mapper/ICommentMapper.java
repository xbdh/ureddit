package com.jicyu.ureddit.comment.provider.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jicyu.ureddit.comment.provider.dao.po.CommentPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICommentMapper extends BaseMapper<CommentPO> {
}
