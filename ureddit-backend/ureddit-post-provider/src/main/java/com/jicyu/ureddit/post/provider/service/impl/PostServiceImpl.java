package com.jicyu.ureddit.post.provider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.jicyu.ureddit.common.utils.ConvertBeanUtils;
import com.jicyu.ureddit.dto.post.PostDTO;
import com.jicyu.ureddit.post.provider.dao.mapper.IPostMapper;
import com.jicyu.ureddit.post.provider.dao.po.PostPO;
import com.jicyu.ureddit.post.provider.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    private IPostMapper postMapper;

    @Autowired
    public void setPostRepository(IPostMapper postRepository) {
        this.postMapper = postRepository;
    }

    @Override
    public String insertPost(PostDTO postDTO) {
        PostPO postPO = ConvertBeanUtils.convert(postDTO,PostPO.class);
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        postPO.setCreateTime(localDateTime);
//        postPO.setUpdateTime(localDateTime);

        postMapper.insert(postPO);
        String id = postPO.getId();
        return id;
    }

    @Override
    public PostDTO findPostById(String id) {
        PostPO postPO = postMapper.selectById(id) ;
        return ConvertBeanUtils.convert(postPO,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPostsByAuthorId(String authorId) {

        LambdaQueryWrapper<PostPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PostPO::getAuthorId,authorId);
        List <PostPO> ls = postMapper.selectList(wrapper);
        return ConvertBeanUtils.convertList(ls,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPostsBySubredditId(String subredditId) {
        LambdaQueryWrapper<PostPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PostPO::getSubredditId,subredditId);
        List <PostPO> ls = postMapper.selectList(wrapper);
        return ConvertBeanUtils.convertList(ls,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPagePostsByAuthorId(int pageNum, int size, String authorId) {
        Page<PostPO> page = new Page<>(pageNum,size);
        page.addOrder(OrderItem.desc("create_time")); //按照创建时间降序排列
        LambdaQueryWrapper<PostPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PostPO::getAuthorId,authorId );

        IPage<PostPO> postPOIPage = postMapper.selectPage(page,wrapper);
        List<PostPO> records = postPOIPage.getRecords();
        return ConvertBeanUtils.convertList(records,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPagePostsBySubredditId(int pageNum, int size, String subredditId) {
        Page<PostPO> page = new Page<>(pageNum,size);
        page.addOrder(OrderItem.desc("create_time")); //按照创建时间降序排列

        LambdaQueryWrapper<PostPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PostPO::getSubredditId,subredditId );
        IPage<PostPO> postPOIPage = postMapper.selectPage(page,wrapper);
        List<PostPO> records = postPOIPage.getRecords();
        return ConvertBeanUtils.convertList(records,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPagePostsInSubredditIds(int pageNum, int size, List<String> subredditIds) {
        Page<PostPO> page = new Page<>(pageNum,size);
        page.addOrder(OrderItem.desc("create_time")); //按照创建时间降序排列

        LambdaQueryWrapper<PostPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(PostPO::getSubredditId,subredditIds );

        IPage<PostPO> postPOIPage = postMapper.selectPage(page,wrapper);
        List<PostPO> records = postPOIPage.getRecords();
        return ConvertBeanUtils.convertList(records,PostDTO.class);
    }

    @Override
    public List<PostDTO> findPagePosts(int pageNum, int size) {
        Page<PostPO> page = new Page<>(pageNum,size);
        page.addOrder(OrderItem.desc("create_time")); //按照创建时间降序排列

        IPage<PostPO> postPOIPage = postMapper.selectPage(page,null);
        List<PostPO> records = postPOIPage.getRecords();
        return ConvertBeanUtils.convertList(records,PostDTO.class);
    }
}
