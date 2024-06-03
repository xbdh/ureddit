package com.jicyu.ureddit.subreddit.provider.dao.po;

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
@TableName("subreddit")
public class SubredditPO {
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    public String id;
    public String name;
    public String creatorId;

    //   @TableField(fill = FieldFill.INSERT) 使用这种方式需要实现某个类，不如在数据表设置建表
    public LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;
}

//public class Subreddit implements Serializable {
//
//}
