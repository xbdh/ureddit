package com.jicyu.ureddit.post.provider.service;

import com.jicyu.ureddit.dto.post.PostDTO;

import java.util.List;

public interface IPostService {

    public String insertPost(PostDTO postDTO);
    public PostDTO findPostById(String id);
    public List<PostDTO> findPostsByAuthorId(String authorId);
    public List<PostDTO> findPostsBySubredditId(String subredditId);

    public List<PostDTO> findPagePostsByAuthorId(int pageNum,int size,String authorId);
    public List<PostDTO> findPagePostsBySubredditId(int pageNum,int size,String subredditId);

    public List<PostDTO> findPagePostsInSubredditIds(int pageNum,int size,List<String> subredditIds);

    public List<PostDTO> findPagePosts(int pageNum,int size);
}
