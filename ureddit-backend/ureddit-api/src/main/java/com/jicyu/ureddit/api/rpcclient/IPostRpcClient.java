package com.jicyu.ureddit.api.rpcclient;

import com.jicyu.ureddit.dto.post.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name ="ureddit-post-provider",path = "post",url = "${rpcClient.postUrl}")
public interface IPostRpcClient {
    @PostMapping("/insertPost")
    String createPost(@RequestBody PostDTO postDTO) ;

    @GetMapping("/findPostById")
    PostDTO findPostById(@RequestParam("id") String id) ;


    @GetMapping("/findPostsByAuthorId")
    List<PostDTO> findPostsByAuthorId(@RequestParam("authorId") String authorId) ;


    @GetMapping("/findPostsBySubredditId")
    List<PostDTO> findPostsBySubredditId(@RequestParam("subredditId") String subredditId) ;

    @GetMapping("/findPagePostsByAuthorId")
    List<PostDTO> findPagePostsByAuthorId(@RequestParam("pageNum") int pageNum,
                                                 @RequestParam("size") int size,
                                                 @RequestParam("authorId") String authorId) ;


    @GetMapping("/findPagePostsBySubredditId")
    List<PostDTO> findPagePostsBySubredditId(@RequestParam("pageNum") int pageNum,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("subredditId") String subredditId) ;
    @GetMapping("/findPagePostsInSubredditIds")
    List<PostDTO> findPagePostsInSubredditIds(@RequestParam("pageNum") int pageNum,
                                                     @RequestParam("size")int size,
                                                     @RequestParam("subredditIds") List<String> subredditIds) ;

    @GetMapping("/findPagePosts")
    public List<PostDTO> findPagePosts(@RequestParam("pageNum") int pageNum,
                                       @RequestParam("size")int size);

}
