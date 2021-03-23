package com.ywy.ltxxzsxt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ywy.ltxxzsxt.domain.CommonResult;
import com.ywy.ltxxzsxt.domain.Users;
import com.ywy.ltxxzsxt.domain.Zhengshus;
import com.ywy.ltxxzsxt.service.UsersService;
import com.ywy.ltxxzsxt.service.ZhengshusService;
import com.ywy.ltxxzsxt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/lt")
public class LoginController {

    @Autowired
    private UsersService userService;
    @Autowired
    private ZhengshusService zhengshuService;

    @GetMapping(value = "/hello")
    @ResponseBody
    private String hello() {
        return "Hello World!";
    }

    @PostMapping(value = "/admin/login")
    @ResponseBody
    private CommonResult login(String userName,String passWord) {
        System.out.println(userName+","+passWord);
        QueryWrapper<Users> qw = new QueryWrapper<Users>();
        qw.eq("userName", userName).eq("password", passWord);
        List<Users> list = userService.list(qw);
        if(list.size() > 0) {
            Users user = list.get(0);
            Map<String, Object> map = new HashMap<>();
            Map<String, String> payload = new HashMap<>();
            payload.put("id", user.getId().toString());
            payload.put("userName", user.getUserName());
            String token = JwtUtils.getToken(payload);
            map.put("status", true);
            map.put("msg", "认证成功");
            map.put("token", token);
            map.put("user", user);
            //更新用户登录时间
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            user.setLoginTime(date.format(new Date()));
            userService.saveOrUpdate(user);
            //response.setHeader("Access-Control-Allow-Origin","*");
            return new CommonResult(200, "登录成功", map);
        }else{
            //response.setHeader("Access-Control-Allow-Origin","*");
            return new CommonResult(400, "登录失败", null);
        }
    }

    //上传文件
    @PostMapping(value = "/admin/upload")
    @ResponseBody
    private String upload(MultipartFile file,String id,String zsId)  {
        Zhengshus zhengshu = new Zhengshus();
        zhengshu = zhengshuService.getById(Integer.parseInt(zsId));
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String suffixname = fileName.substring(fileName.lastIndexOf("."));//获取文件后缀名
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        //证书附件的名称由：活动名称+活动项目+奖次+学生姓名+指导教师姓名+id+后缀名
        fileName = zhengshu.getHdmc()+"+"+zhengshu.getHdxm()+"+"+zhengshu.getJcname()+"+"+zhengshu.getStudentname()+"+"+zhengshu.getZhidaoteacher()+"+"+zhengshu.getId()+suffixname;
// String filePath = request.getSession().getServletContext().getRealPath("templates/imgupload/");
        //指定文件存放路径，可以是相对路径或者绝对路径
//        String filePath = "./src/main/resources/upload/";
        String filePath = "G:/zs/";
        zhengshu.setPicdizhi(filePath+fileName);
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            zhengshuService.updateById(zhengshu);
            //返回json
            return "success";
        } catch (Exception e) {
            // TODO: handle exception
            return "fail";
        }
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    //前台图片预览接口
    @GetMapping(value = "/admin/show")
    @ResponseBody
    private void show(HttpServletResponse response, String zsId) throws IOException {
        Zhengshus zhengshu;
        zhengshu = zhengshuService.getById(Integer.parseInt(zsId));
        System.out.println(zhengshu.getPicdizhi());
        String realPath = zhengshu.getPicdizhi();
        if(realPath == null || realPath.length() == 0){
            realPath = "G:/zs/未上传.jpg";
            System.out.println("本条记录未上传图片");
        }
        String suffixname = realPath.substring(realPath.lastIndexOf(".")+1);//获取文件后缀名
        FileInputStream inputStream = new FileInputStream(realPath);
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        System.out.println(suffixname);
        if(suffixname.endsWith("pdf")){
            response.setContentType("application/pdf");
        }else{
            response.setContentType("image/*");
        }
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }

    @PostMapping(value = "/admin/test")
    @ResponseBody
    private boolean test(String token) {
        boolean b;
        b = JwtUtils.verify(token);
        System.out.println(b);
        return b;
    }
}