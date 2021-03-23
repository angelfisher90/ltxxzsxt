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
 * user
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class Users implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value="userName")
    private String userName;

    /**
     * 密码
     */
    @TableField(value="passWord")
    private String passWord;

    /**
     * 用户权限
     */
    @TableField(value="auth")
    private Integer auth;

    /**
     * 部门代码
     */
    @TableField(value="partCode")
    private String partCode;

    /**
     * 新增时间
     */
    @TableField(value="inTime")
    private String inTime;

    /**
     * 最后一次登陆时间
     */
    @TableField(value="loginTime")
    private String loginTime;

    private static final long serialVersionUID = 1L;
}