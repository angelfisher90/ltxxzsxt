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
 * cailiao
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`cailiao`")
public class Cailiao implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 证明材料名称
     */
    @TableField(value="clName")
    private String clname;

    /**
     * 证明材料备注
     */
    @TableField(value="clBeizhu")
    private String clbeizhu;

    private static final long serialVersionUID = 1L;
}