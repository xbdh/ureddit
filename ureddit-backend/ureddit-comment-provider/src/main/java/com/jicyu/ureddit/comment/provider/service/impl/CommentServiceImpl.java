package com.jicyu.ureddit.comment.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jicyu.ureddit.comment.provider.dao.mapper.ICommentMapper;
import com.jicyu.ureddit.comment.provider.dao.po.CommentPO;
import com.jicyu.ureddit.comment.provider.service.ICommentService;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl  implements ICommentService {
    private ICommentMapper commentMapper;
    @Autowired
    public CommentServiceImpl(ICommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public String insertComment(CommentDTO commentDTO) {
        CommentPO commentPO = ConvertBeanUtils.convert(commentDTO, CommentPO.class);
        commentMapper.insert(commentPO);
        return commentPO.getId();
    }

    @Override
    public List<CommentDTO> findCommentsByPostId(String postId) {
        LambdaQueryWrapper<CommentPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentPO::getPostId,postId);
        wrapper.orderByDesc(CommentPO::getCreateTime);
        List<CommentPO> commentPOList = commentMapper.selectList(wrapper);
        List<CommentDTO> ls = ConvertBeanUtils.convertList(commentPOList, CommentDTO.class);
        return ls;
    }

    @Override
    public List<CommentDTO> findRootCommentsByPostId(String postId) {
        LambdaQueryWrapper<CommentPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentPO::getPostId,postId);
        wrapper.isNull(CommentPO::getReplyToId);
        wrapper.orderByDesc(CommentPO::getCreateTime);
        List<CommentPO> commentPOList = commentMapper.selectList(wrapper);
        List<CommentDTO> ls = ConvertBeanUtils.convertList(commentPOList, CommentDTO.class);
        return ls;
    }

    @Override
    public List<CommentDTO> findChildrenCommentsByIdWithParentId(String postId, String parentId) {
        LambdaQueryWrapper<CommentPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentPO::getPostId,postId);
        wrapper.eq(CommentPO::getReplyToId,parentId);
        wrapper.orderByDesc(CommentPO::getCreateTime);
        List<CommentPO> commentPOList = commentMapper.selectList(wrapper);
        List<CommentDTO> ls = ConvertBeanUtils.convertList(commentPOList, CommentDTO.class);
        return ls;
    }

    @Override
    public long countCommentsByPostId(String postId) {
        LambdaQueryWrapper<CommentPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CommentPO::getPostId,postId);

        return commentMapper.selectCount(wrapper);
    }
}
