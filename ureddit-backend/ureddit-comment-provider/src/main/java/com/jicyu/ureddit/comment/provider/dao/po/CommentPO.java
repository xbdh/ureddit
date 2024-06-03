package com.jicyu.ureddit.comment.provider.dao.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("comment")
public class CommentPO {
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    String id;
    String postId;
    String userId;
    String content;
    String replyToId;
//    int level;
    public LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public  LocalDateTime updateTime;
}
