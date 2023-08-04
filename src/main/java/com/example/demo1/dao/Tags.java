package com.example.demo1.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("segmentjson")
public class Tags {

    @TableField("campaign")
    private String campaign;

    @TableField("segment")
    private String segment;

    @TableField("segmentjson")
    private String segmentjson;
}
