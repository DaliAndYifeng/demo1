package com.example.demo1.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dyh_test")
public class dyhTest {

    @TableField("activityid")
    private String activityid;
    @TableField("startmemberid")
    private String startmemberid;
    @TableField("helpmemberid")
    private String helpmemberid;
    @TableField("nick")
    private String nick;
    @TableField("icon")
    private String icon;
    @TableField("hide_err")
    private String hideErr;
    @TableField("ownerid")
    private String ownerid;

}
