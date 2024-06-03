package com.jicyu.ureddit.comment.provider.rpc;


import com.jicyu.ureddit.comment.provider.service.ICommentService;
import com.jicyu.ureddit.dto.comment.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentRpcController {
    private ICommentService commentService;

    @Autowired
    public void setCommentService(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/insertComment")
    public String insertComment(@RequestBody CommentDTO commentDTO) {
        return commentService.insertComment(commentDTO);
    }

    @GetMapping("/findCommentsByPostId")
    public List<CommentDTO> findCommentsByPostId(@RequestParam("postId") String postId) {
        return commentService.findCommentsByPostId(postId);
    }

    @GetMapping("/findRootCommentsByPostId")
    public List<CommentDTO> findRootCommentsByPostId(@RequestParam("postId") String postId) {
        return commentService.findRootCommentsByPostId(postId);
    }
    @GetMapping("/findChildrenCommentsByIdWithParentId")
    public List<CommentDTO> findChildrenCommentsByIdWithParentId(@RequestParam("postId") String postId,@RequestParam("parentId") String parentId) {
        return commentService.findChildrenCommentsByIdWithParentId(postId,parentId);
    }
    @GetMapping("/countCommentsByPostId")
    public long countCommentsByPostId(@RequestParam("postId") String postId) {
        return commentService.countCommentsByPostId(postId);
    }

}
