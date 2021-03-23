package com.ywy.ltxxzsxt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywy.ltxxzsxt.domain.CommonResult;
import com.ywy.ltxxzsxt.domain.Zhengshus;
import com.ywy.ltxxzsxt.service.ZhengshusService;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin()
@RestController
@RequestMapping(value = "/zs")
public class ZhengShuController {

    @Autowired
    private ZhengshusService zhengshuService;

    @GetMapping(value = "/hello")
    @ResponseBody
    private String hello() {
        return "Hello World!";
    }
    //查询所有证书
    @PostMapping(value = "/admin/all")
    @ResponseBody
    private CommonResult all(String token,String userId) {
        System.out.println("zheng shu quan bu cha xun api!");
        List<Zhengshus> zs;
        if(userId.endsWith("1")){
            zs = zhengshuService.list();
        }else{
            QueryWrapper<Zhengshus> zsQW = new QueryWrapper<Zhengshus>();
            zsQW.like("userId",userId);
            zs = zhengshuService.list(zsQW);
        }
        if(zs.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            if(JwtUtils.verify(token)){
                map.put("status", true);
                map.put("msg", "查询成功");
                map.put("zs", zs);
                return new CommonResult(200, "查询成功", map);
            }else{
                return new CommonResult(400, "token错误", null);
            }
        }else{
            return new CommonResult(400, "查询失败", null);
        }
    }

    //根据条件查询证书
    @PostMapping(value = "/admin/selectBYterm")
    @ResponseBody
    private CommonResult selectBYterm(String token,String hdmc,String hdxm,String jbName,String jcName,String studentName,String zhidaoTeacher,String fazhengTime,String userId) throws ParseException {
        if(hdmc.length() == 0 && hdxm.length() == 0 && jbName.length() == 0 && jcName.length() == 0 && studentName.length() == 0 && zhidaoTeacher.length() == 0 && fazhengTime.length() == 0){
            return all(token,userId);
        }else{
            System.out.println("zheng shu select by term api!");
            boolean hdmcB = true,hdxmB = true,jbNameB = true,jcNameB = true,studentNameB = true,zhidaoTeacherB = true,fazhengTimeB = true,userIdB = true;
            if(hdmc.length() == 0){ hdmcB = false; }
            if(hdxm.length() == 0){ hdxmB = false; }
            if(jbName.length() == 0){ jbNameB = false;}
            if(jcName.length() == 0){ jcNameB = false;}
            if(studentName.length() == 0){ studentNameB = false;}
            if(zhidaoTeacher.length() == 0){ zhidaoTeacherB = false;}
            if(fazhengTime.length() == 0){ fazhengTimeB = false;}
            if(userId.endsWith("1")){ userIdB = false;}
            List<Zhengshus> zs;
            QueryWrapper<Zhengshus> zsQW = new QueryWrapper<Zhengshus>();
            zsQW.like(hdmcB,"hdmc",hdmc).like(hdxmB,"hdxm",hdxm).like(jbNameB,"jbName",jbName).like(jcNameB,"jcName",jcName).like(studentNameB,"studentName",studentName).like(zhidaoTeacherB,"zhidaoTeacher",zhidaoTeacher).like(fazhengTimeB,"fazhengTime",fazhengTime).like(userIdB,"userId",userId);
            zs = zhengshuService.list(zsQW);
            if(zs.size() == 0){
                return new CommonResult(406, "无符合条件的数据", null);
            }else{
                if(zs.size() > 0) {
                    if(JwtUtils.verify(token)){
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", true);
                        map.put("msg", "查询成功");
                        map.put("zs",zs);
                        return new CommonResult(200, "查询成功", map);
                    }else{
                        return new CommonResult(405, "token错误", null);
                    }
                }else{
                    return new CommonResult(400, "查询失败", null);
                }
            }
        }
    }

    //编辑保存接口
    @PostMapping(value = "/admin/editSave")
    @ResponseBody
    private CommonResult editSave(String token,String id,String hdmc,String hdxm,String jbName,String jcName,String studentName,String zhidaoTeacher,String zbName,String fazhengTime,String clName,String zsNum,String beizhu) {
        System.out.println("zheng shu edit save api!");
        Zhengshus zs = new Zhengshus();
        zs.setId(Integer.parseInt(id));
        zs.setHdmc(hdmc);
        zs.setHdxm(hdxm);
        zs.setJbname(jbName);
        zs.setJcname(jcName);
        zs.setStudentname(studentName);
        zs.setZhidaoteacher(zhidaoTeacher);
        zs.setZbname(zbName);
        zs.setFazhengtime(fazhengTime);
        zs.setClname(clName);
        zs.setZsnum(zsNum);
        zs.setBeizhu(beizhu);
        if(zhengshuService.updateById(zs)) {
            if(JwtUtils.verify(token)){
                Map<String, Object> map = new HashMap<>();
                map.put("status", true);
                map.put("msg", "更新成功");
                return new CommonResult(200, "更新成功", map);
            }else{
                return new CommonResult(405, "token错误", null);
            }
        }else{
            return new CommonResult(400, "更新失败", null);
        }
    }

    //新增接口
    @PostMapping(value = "/admin/insertSave")
    @ResponseBody
    private CommonResult insertSave(String token, String hdmc, String hdxm, String jbName, String jcName, String studentName, String zhidaoTeacher, String zbName, String fazhengTime, String clName, String zsNum, String beizhu, String userId, MultipartFile file) {
        System.out.println("zheng shu insert api!");

        String filePath = "D:/1/";
        String fileName = file.getOriginalFilename();
        String suffixname = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
        //证书附件的名称由：活动名称+活动项目+奖次+学生姓名+指导教师姓名+id+后缀名
        fileName = hdmc+"+"+hdxm+"+"+jcName+"+"+studentName+"+"+zhidaoTeacher+"+"+suffixname;

        Zhengshus zs = new Zhengshus();
        zs.setHdmc(hdmc);
        zs.setHdxm(hdxm);
        zs.setJbname(jbName);
        zs.setJcname(jcName);
        zs.setStudentname(studentName);
        zs.setZhidaoteacher(zhidaoTeacher);
        zs.setZbname(zbName);
        zs.setFazhengtime(fazhengTime);
        zs.setClname(clName);
        zs.setZsnum(zsNum);
        zs.setBeizhu(beizhu);
        zs.setUserid(Integer.parseInt(userId));
        zs.setPicdizhi(filePath+fileName);
        if(zhengshuService.save(zs)) {
            if(JwtUtils.verify(token)){
                try {
                    uploadFile(file.getBytes(), filePath, fileName);
                } catch (Exception e) {

                }
                Map<String, Object> map = new HashMap<>();
                map.put("status", true);
                map.put("msg", "新增成功");
                return new CommonResult(200, "新增成功", map);
            }else{
                return new CommonResult(405, "token错误", null);
            }
        }else{
            return new CommonResult(400, "新增失败", null);
        }
    }

    //删除接口
    @PostMapping(value = "/admin/deleteRow")
    @ResponseBody
    private CommonResult deleteRow(String token,String id) {
        System.out.println("zheng shu delete api!");
        Zhengshus zs = new Zhengshus();
        Zhengshus zsDelete;
        String fileName;
        zs.setId(Integer.parseInt(id));
        zsDelete = zhengshuService.getById(Integer.parseInt(id));
        fileName = zsDelete.getPicdizhi();
        File file = new File(fileName);
        if(zhengshuService.removeById(zs)) {
            if(JwtUtils.verify(token)){
                if(fileName.length() != 0){
                    file.delete();
                }
                Map<String, Object> map = new HashMap<>();
                map.put("status", true);
                map.put("msg", "删除成功");
                return new CommonResult(200, "删除成功", map);
            }else{
                return new CommonResult(405, "token错误", null);
            }
        }else{
            return new CommonResult(400, "删除失败", null);
        }
    }

//上传图片方法
    private static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}