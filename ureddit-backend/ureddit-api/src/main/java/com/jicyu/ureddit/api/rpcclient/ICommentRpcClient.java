package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.comment.CommentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ureddit-comment-provider",path = "comment"  ,url = "${rpcClient.commentUrl}")
public interface ICommentRpcClient {
    @PostMapping("/insertComment")
    public String insertComment(@RequestBody CommentDTO commentDTO) ;

    @GetMapping("/findCommentsByPostId")
    public List<CommentDTO> findCommentsByPostId(@RequestParam("postId") String postId);

    @GetMapping("/findRootCommentsByPostId")
    public List<CommentDTO> findRootCommentsByPostId(@RequestParam("postId") String postId);
    @GetMapping("/findChildrenCommentsByIdWithParentId")
    public List<CommentDTO> findChildrenCommentsByIdWithParentId(@RequestParam("postId") String postId, @RequestParam("parentId") String parentId) ;

    @GetMapping("/countCommentsByPostId")
    public long countCommentsByPostId(@RequestParam("postId") String postId) ;
}
