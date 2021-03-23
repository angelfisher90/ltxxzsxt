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
 * jibie
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`jibie`")
public class Jibie implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 级别名称
     */
    @TableField(value="jbname")
    private String jbname;

    /**
     * 级别备注
     */
    @TableField(value="jbbeizhu")
    private String jbbeizhu;

    private static final long serialVersionUID = 1L;
}