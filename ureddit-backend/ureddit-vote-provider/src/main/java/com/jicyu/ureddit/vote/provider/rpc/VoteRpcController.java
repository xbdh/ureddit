package com.jicyu.ureddit.vote.provider.rpc;

import com.jicyu.ureddit.dto.vote.VoteDTO;
import com.jicyu.ureddit.vote.provider.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteRpcController {
    private IVoteService voteService;

    @Autowired
    public VoteRpcController(IVoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/createVote")
    public Boolean createVote(@RequestBody VoteDTO voteDTO  ) {
        return voteService.createVote(voteDTO);
    }

    @PostMapping("/deleteVote")
    public Boolean deleteVote(@RequestBody VoteDTO voteDTO) {
        return voteService.deleteVote(voteDTO);
    }

    @GetMapping("/getVoteCount")
    public Long getVoteCount(@RequestParam("postId") String postId, @RequestParam("voteType") String voteType) {
        return voteService.getVoteCount(postId,voteType);
    }

    @GetMapping("/getCurrentVote")
    public VoteDTO getCurrentVote(@RequestParam("postId") String postId, @RequestParam("userId") String userId) {
        return voteService.getCurrentVote(postId,userId);
    }

    @PostMapping("/updateVote")
    public Boolean updateVote(@RequestBody VoteDTO voteDTO) {
        return voteService.updateVote(voteDTO);
    }
}
