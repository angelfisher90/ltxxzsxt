package com.ywy.ltxxzsxt.controller;

import com.ywy.ltxxzsxt.domain.CommonResult;
import com.ywy.ltxxzsxt.domain.Jibie;
import com.ywy.ltxxzsxt.service.JibieService;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/jb")
public class JiBieController {

    @Autowired
    private JibieService jibieService;

    @GetMapping(value = "/hello")
    @ResponseBody
    private String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/admin/queryJiBie")
    @ResponseBody
    private CommonResult queryJiBie(String token) {
        System.out.println("cha xun jibie lie biao!");
        List<Jibie> jb = jibieService.list();
        if(jb.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            if(JwtUtils.verify(token)){
                map.put("status", true);
                map.put("msg", "查询成功");
                map.put("jb", jb);
                return new CommonResult(200, "查询成功", map);
            }else{
                return new CommonResult(400, "token错误", null);
            }
        }else{
            return new CommonResult(400, "查询失败", null);
        }
    }

    @PostMapping(value = "/admin/insertJiBie")
    @ResponseBody
    private CommonResult insertJiBie(String token,String jbName) {
        System.out.println("xin zeng jibie lie biao!");
        Jibie jb = new Jibie();
        jb.setJbname(jbName);
        if(JwtUtils.verify(token)){
            if(jibieService.save(jb)) {
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