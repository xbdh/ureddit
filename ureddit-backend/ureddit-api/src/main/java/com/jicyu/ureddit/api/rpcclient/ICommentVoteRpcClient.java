package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.vote.CommentVoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ureddit-comment-vote-provider",path = "commentVote"  ,url = "${rpcClient.voteUrl}")
public interface ICommentVoteRpcClient {
    @PostMapping("/createCommentVote")
    Boolean createCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) ;
    @PostMapping("/deleteCommentVote")
    Boolean deleteCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) ;

    @GetMapping("/getCommentVoteCount")
    Long getCommentVoteCount(@RequestParam("commentId") String commentId,
                                    @RequestParam("voteType") String voteType);


    @GetMapping("/getCurrentCommentVote")
    CommentVoteDTO getCurrentCommentVote(@RequestParam("commentId") String commentId,
                                                @RequestParam("userId") String userId) ;

    @PostMapping("/updateCommentVote")
    Boolean updateCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) ;
}
