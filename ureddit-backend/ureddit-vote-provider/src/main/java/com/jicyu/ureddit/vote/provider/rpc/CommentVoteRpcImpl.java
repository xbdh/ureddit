package com.jicyu.ureddit.vote.provider.rpc;


import com.jicyu.ureddit.dto.vote.CommentVoteDTO;
import com.jicyu.ureddit.vote.provider.service.ICommentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentVote")
public class CommentVoteRpcImpl {
    private ICommentVoteService commentVoteService;

    @Autowired
    public void setCommentVoteService(ICommentVoteService commentVoteService) {
        this.commentVoteService = commentVoteService;
    }

    @PostMapping("/createCommentVote")
    public Boolean createCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) {
        return commentVoteService.createCommentVote(commentvoteDTO);
    }
    @PostMapping("/deleteCommentVote")
    public Boolean deleteCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) {
        return commentVoteService.deleteCommentVote(commentvoteDTO);
    }

    @GetMapping("/getCommentVoteCount")
    public Long getCommentVoteCount(@RequestParam("commentId") String commentId,
                                    @RequestParam("voteType") String voteType) {
        return commentVoteService.getCommentVoteCount(commentId, voteType);
    }

    @GetMapping("/getCurrentCommentVote")
    public CommentVoteDTO getCurrentCommentVote(@RequestParam("commentId") String commentId,
                                                @RequestParam("userId") String userId) {
        return commentVoteService.getCurrentCommentVote(commentId, userId);
    }

    @PostMapping("/updateCommentVote")
    public Boolean updateCommentVote(@RequestBody CommentVoteDTO commentvoteDTO) {
        return commentVoteService.updateCommentVote(commentvoteDTO);
    }
}
