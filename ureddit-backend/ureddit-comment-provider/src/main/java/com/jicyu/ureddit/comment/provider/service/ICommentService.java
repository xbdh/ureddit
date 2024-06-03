package com.jicyu.ureddit.comment.provider.service;

import com.jicyu.ureddit.dto.comment.CommentDTO;

import java.util.List;

public interface ICommentService {
    String insertComment(CommentDTO commentDTO);

    List<CommentDTO> findCommentsByPostId(String postId);
    List<CommentDTO> findRootCommentsByPostId(String postId);
    List<CommentDTO> findChildrenCommentsByIdWithParentId(String postId,String parentId);

    long countCommentsByPostId(String postId);
}
