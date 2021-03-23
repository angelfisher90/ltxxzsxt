package com.ywy.ltxxzsxt.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jiangci
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`jiangci`")
public class Jiangci implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 奖次名称
     */
    @TableField(value="jcname")
    private String jcname;

    /**
     * 奖次备注
     */
    @TableField(value="jcbeizhu")
    private String jcbeizhu;

    private static final long serialVersionUID = 1L;
}