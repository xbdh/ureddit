package com.jicyu.ureddit.dto.subreddit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDTO implements Serializable {
    public String id;
    public String name;
    public String creatorId;

    //   @TableField(fill = FieldFill.INSERT) 使用这种方式需要实现某个类，不如在数据表设置建表
    public LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;
}
