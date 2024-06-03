package com.jicyu.ureddit.post.provider.rpc;


import com.jicyu.ureddit.dto.post.PostDTO;

import com.jicyu.ureddit.post.provider.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post")
public class PostRpcController  {
    private IPostService postService;

    @Autowired
    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping("/insertPost")
    public String createPost(@RequestBody PostDTO postDTO) {
        return postService.insertPost(postDTO);
    }

    @GetMapping("/findPostById")
    public PostDTO findPostById(@RequestParam("id") String id) {
        return postService.findPostById(id);
    }

    @GetMapping("/findPostsByAuthorId")
    public List<PostDTO> findPostsByAuthorId(@RequestParam("authorId") String authorId) {
        return postService.findPostsByAuthorId(authorId);
    }

    @GetMapping("/findPostsBySubredditId")
    public List<PostDTO> findPostsBySubredditId(@RequestParam("subredditId") String subredditId) {
        return postService.findPostsBySubredditId(subredditId);
    }

    @GetMapping("/findPagePostsByAuthorId")
    public List<PostDTO> findPagePostsByAuthorId(@RequestParam("pageNum") int pageNum,
                                                 @RequestParam("size") int size,
                                                 @RequestParam("authorId") String authorId) {
        return postService.findPagePostsByAuthorId(pageNum,size,authorId);
    }

    @GetMapping("/findPagePostsBySubredditId")
    public List<PostDTO> findPagePostsBySubredditId(@RequestParam("pageNum") int pageNum,
                                                    @RequestParam("size") int size,
                                                    @RequestParam("subredditId") String subredditId) {
       return postService.findPagePostsBySubredditId(pageNum,size,subredditId);
    }

    @GetMapping("/findPagePostsInSubredditIds")
    public List<PostDTO> findPagePostsInSubredditIds(@RequestParam("pageNum") int pageNum,
                                                     @RequestParam("size")int size,
                                                     @RequestParam("subredditIds") List<String> subredditIds){
        return postService.findPagePostsInSubredditIds(pageNum,size,subredditIds);
    }

    @GetMapping("/findPagePosts")
    public List<PostDTO> findPagePosts(@RequestParam("pageNum") int pageNum,
                                       @RequestParam("size")int size){
        return postService.findPagePosts(pageNum,size);
    }
}
