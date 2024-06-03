package com.jicyu.ureddit.vote.provider.dao.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote")
public class VotePO {

    public String userId;
    public String postId;
    public String voteType;
    //   @TableField(fill = FieldFill.INSERT) 使用这种方式需要实现某个类，不如在数据表设置建表
    public LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;
}
