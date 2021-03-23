package com.ywy.ltxxzsxt.controller;

import com.ywy.ltxxzsxt.domain.CommonResult;
import com.ywy.ltxxzsxt.domain.Jiangci;
import com.ywy.ltxxzsxt.service.JiangciService;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/jc")
public class JiangCiController {

    @Autowired
    private JiangciService jiangciService;

    @GetMapping(value = "/hello")
    @ResponseBody
    private String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/admin/queryJiangCi")
    @ResponseBody
    private CommonResult queryJiBie(String token) {
        System.out.println("cha xun jiangci lie biao!");
        List<Jiangci> jc = jiangciService.list();
        if(jc.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            if(JwtUtils.verify(token)){
                map.put("status", true);
                map.put("msg", "查询成功");
                map.put("jc", jc);
                return new CommonResult(200, "查询成功", map);
            }else{
                return new CommonResult(400, "token错误", null);
            }
        }else{
            return new CommonResult(400, "查询失败", null);
        }
    }

    @PostMapping(value = "/admin/insertJiangCi")
    @ResponseBody
    private CommonResult insertJiangCi(String token,String jcName) {
        System.out.println("xin zeng jiangci lie biao!");
        Jiangci jc = new Jiangci();
        jc.setJcname(jcName);
        if(JwtUtils.verify(token)){
            if(jiangciService.save(jc)) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", true);
                map.put("msg", "查询成功");
                return new CommonResult(200, "新增成功", map);
            }else{
                return new CommonResult(405, "新增失败", null);
            }
        }else{
            return new CommonResult(400, "token错误", null);
        }
    }
}