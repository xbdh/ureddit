package com.jicyu.ureddit.subscription.provider.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("subscription")
public class SubscriptionPO {
    @TableId(value = "id",type= IdType.ASSIGN_UUID)
    public String id;
    public String userId;
    public String subredditId;
}


