package com.jicyu.ureddit.interfaces.post;


import com.jicyu.ureddit.dto.post.PostDTO;

import java.util.List;
import java.util.Optional;

public interface IPostRpc {

    public String createPost(PostDTO postDTO);
    public PostDTO findPostById(String id);
    public List<PostDTO> findPostsByAuthorId(String authorId);
    public List<PostDTO> findPostsBySubredditId(String subredditId);
    public List<PostDTO> findPagePostsByAuthorId(int pageNum,int size,String authorId);
    public List<PostDTO> findPagePostsBySubredditId(int pageNum,int size,String subredditId);
}
