package com.ywy.ltxxzsxt.domain;

/**
 * @description: TODO
 * @author: Wenqu
 * @date: 2021/1/12 21:24
 * @version: 1.0.0
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code; //返回代码 如404
    private String message; //返回信息
    private T data; //返回数据
    //定义只有两个参数的构造方法
    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

}