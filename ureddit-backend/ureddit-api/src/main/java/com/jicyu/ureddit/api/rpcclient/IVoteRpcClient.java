package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.vote.VoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="ureddit-vote-provider",path="vote",url = "${rpcClient.voteUrl}")
public interface IVoteRpcClient {
    @PostMapping("/createVote")
    Boolean createVote(@RequestBody VoteDTO voteDTO  ) ;
    @PostMapping("/deleteVote")
    Boolean deleteVote(@RequestBody VoteDTO voteDTO);

    @GetMapping("/getVoteCount")
    Long getVoteCount(@RequestParam("postId") String postId, @RequestParam("voteType") String voteType);


    @GetMapping("/getCurrentVote")
    VoteDTO getCurrentVote(@RequestParam("postId") String postId, @RequestParam("userId") String userId) ;

    @PostMapping("/updateVote")
    Boolean updateVote(@RequestBody VoteDTO voteDTO);
}
