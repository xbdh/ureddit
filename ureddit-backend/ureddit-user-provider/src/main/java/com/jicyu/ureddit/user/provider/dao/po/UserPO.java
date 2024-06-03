package com.jicyu.ureddit.user.provider.dao.po;

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
@TableName("user")
public class UserPO  {
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    public String id;
    public String userId;
    public String username;
    public String image;
    //   @TableField(fill = FieldFill.INSERT) 使用这种方式需要实现某个类，不如在数据表设置建表
    public LocalDateTime createTime;
    //   @TableField(fill= FieldFill.INSERT_UPDATE)
    public LocalDateTime updateTime;
}
