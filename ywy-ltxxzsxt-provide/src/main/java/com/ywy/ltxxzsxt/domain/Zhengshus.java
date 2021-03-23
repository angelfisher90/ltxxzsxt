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
 * zhengshu
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`zhengshu`")
public class Zhengshus implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 活动名称
     */
    @TableField(value="hdmc")
    private String hdmc;

    /**
     * 活动项目
     */
    @TableField(value="hdxm")
    private String hdxm;

    /**
     * 级别名称
     */
    @TableField(value="jbname")
    private String jbname;

    /**
     * 奖次名称
     */
    @TableField(value="jcname")
    private String jcname;

    /**
     * 获奖学生
     */
    @TableField(value="studentname")
    private String studentname;

    /**
     * 指导教师
     */
    @TableField(value="zhidaoteacher")
    private String zhidaoteacher;

    /**
     * 主办机构
     */
    @TableField(value="zbname")
    private String zbname;

    /**
     * 发证时间
     */
    @TableField(value="fazhengtime")
    private String fazhengtime;

    /**
     * 证明材料
     */
    @TableField(value="clname")
    private String clname;

    /**
     * 证书号码
     */
    @TableField(value="zsnum")
    private String zsnum;

    /**
     * 证书地址
     */
    @TableField(value="picdizhi")
    private String picdizhi;

    /**
     * 证书版式
     */
    @TableField(value="zsbanshi")
    private String zsbanshi;

    /**
     * 证书备注
     */
    @TableField(value="beizhu")
    private String beizhu;

    /**
     * 用户id
     */
    @TableField(value="userid")
    private Integer userid;

    private static final long serialVersionUID = 1L;
}