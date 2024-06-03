package com.jicyu.ureddit.post.provider.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("post")
public class PostPO{
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    String id;
    String title;
    String content;
    String authorId;
    String subredditId;
    //   @TableField(fill = FieldFill.INSERT) 使用这种方式需要实现某个类，不如在数据表设置建表
//    public transient LocalDateTime createTime;
//    //   @TableField(fill= FieldFill.INSERT_UPDATE)
//    public transient LocalDateTime updateTime;
    public  LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public  LocalDateTime updateTime;
}
