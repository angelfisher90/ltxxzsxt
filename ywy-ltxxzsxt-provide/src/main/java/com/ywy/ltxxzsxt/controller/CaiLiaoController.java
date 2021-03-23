package com.ywy.ltxxzsxt.controller;

import com.ywy.ltxxzsxt.domain.Cailiao;
import com.ywy.ltxxzsxt.domain.CommonResult;
import com.ywy.ltxxzsxt.service.CailiaoService;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cl")
public class CaiLiaoController {

    @Autowired
    private CailiaoService cailiaoService;

    @GetMapping(value = "/hello")
    @ResponseBody
    private String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/admin/queryCaiLiao")
    @ResponseBody
    private CommonResult queryJiBie(String token) {
        System.out.println("cha xun cailiao lie biao!");
        List<Cailiao> cl = cailiaoService.list();
        if(cl.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            if(JwtUtils.verify(token)){
                map.put("status", true);
                map.put("msg", "查询成功");
                map.put("cl", cl);
                return new CommonResult(200, "查询成功", map);
            }else{
                return new CommonResult(400, "token错误", null);
            }
        }else{
            return new CommonResult(400, "查询失败", null);
        }
    }

    @PostMapping(value = "/admin/insertCaiLiao")
    @ResponseBody
    private CommonResult insertCaiLiao(String token,String clName) {
        System.out.println("xin zeng cailiao lie biao!");
        Cailiao cl = new Cailiao();
        cl.setClname(clName);
        if(JwtUtils.verify(token)){
            if(cailiaoService.save(cl)) {
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