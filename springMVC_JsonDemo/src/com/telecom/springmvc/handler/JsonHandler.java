package com.telecom.springmvc.handler;

import com.telecom.springmvc.beans.Employee;
import com.telecom.springmvc.dao.DepartmentDao;
import com.telecom.springmvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @Author:KUN
 * @Data:2021/5/21 18:12
 * @Description: 测试SpringMVC处理JSON数据、测试拦截器执行顺序
 * @Version:1.0
 */

@Controller
public class JsonHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;


    /**
     * 处理Json
     */
    @ResponseBody
    @RequestMapping("/testJson")
    public Collection<Employee> testJson(){
        Collection<Employee> emps = employeeDao.getAll();
        //以前常规处理：将集合emps存入Request域，转发到JSP页面，最后将Jsp页面传送到浏览器，这个过程比较慢
        //可以考虑以Json的方式传输
        //      javaWeb做法：Gson gson.toJson(emps); out.println(jsonStr);
        //      springMVC做法：使用@ResponseBody 将返回值转换成Json字符串，响应给浏览器端。
        //Json的应用场景：
        //      通过异步请求，获取数据，再通过js技术动态的更新到页面，以表格或者其他形式显示出来。
        return emps;
    }

    /**
     * 使用HttpMessageConveter完成文件下载功能
     * 支持@RequestBody、@ResponseBody、HttpEntity、ResponseEntity
     * 下载原理：将服务器端的文件以流的形式写到客户端。
     * ResponseEntity：将要下载的文件以及相应星系封装到ResponseEntity对象中，浏览器通过解析
     *                 发送回去的数据，就可以进行下一个下载操作。
     */
    @RequestMapping("/download")
    public ResponseEntity<byte[]> testDownload(HttpSession session) throws Exception{
        //获取输入流
        ServletContext sc = session.getServletContext();
        InputStream ips = sc.getResourceAsStream("images/wave.jpeg");
        //将要下载的文件读成一个字节数组
        byte[] imgs = new byte[ips.available()];
        System.out.println(ips.available());
        ips.read(imgs);

        //主动（手动）向浏览器发送数据，区别于服务器自动向浏览器发送数据
        //将响应数据以及一些响应头信息封装到ResponseEntity : new ResponseEntity<byte[]>(body,headers,statusCode)
        //参数：
        //  1、body：发送给浏览器端的数据
        //  2、headers：设置响应头
        //  3、statusCode：设置响应码
        byte[] body = imgs;
        HttpHeaders headers = new HttpHeaders();
        //"Content-Disposition"是消息头固定写法、attachment表示附件、filename用于指定文件名
        headers.add("Content-Disposition","attachment;filename=songlaoshi.jpg");
        HttpStatus statusCode = HttpStatus.OK; //200
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(body, headers, statusCode);
        return responseEntity;
    }

    /**
     * 文件的上传
     * 上传原理：将本地的文件上传到服务器
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String testUpload(@RequestParam("desc")String desc,
                             @RequestParam("uploadFile") MultipartFile uploadFile,
                            HttpSession session) throws Exception {
        //获取上传文件名
        String uploadFileName = uploadFile.getOriginalFilename();
        //获取服务器端的uploads文件夹的真实路径
        ServletContext sc = session.getServletContext();
        String realPath = sc.getRealPath("uploads");
        File targetFile = new File(realPath + "/" + uploadFileName);
        /*传统方法：
        //获取输入流
        InputStream in = uploadFile.getInputStream();
        //获取输出流
        FileOutputStream os = new FileOutputStream(targetFile);
        //从输入流读取数据，然后再用输出流写入文件
        int i;
        while((i = in.read())!=-1){
            os.write(i);
        }
        in.close();
        os.close();*/

        //MultipartFile提供的文件上传的方法
        uploadFile.transferTo(targetFile);

        return "success";
    }

    /**
     * 测试拦截器
     */
    @RequestMapping(value = "/testInterceptor")
    public String testInterceptor(){
        return "success";
    }


















}

/*视图和视图解析器：
1） 请求处理方法执行完成后，最终返回一个 ModelAndView 对象。对于那些返回 String，View 或 ModeMap
    等类型的处理方法，Spring MVC 也会在内部将它们装配成一个 ModelAndView 对象，它包含了逻辑名和
    模型对象的视图
2） Spring MVC 借助视图解析器（ViewResolver）得到最终的视图对象（View），最终的视图可以是 JSP ，
    也可能是 Excel、JFreeChart等各种表现形式的视图
3） 对于最终究竟采取何种视图对象对模型数据进行渲染，处理器并不关心，处理器工作重点聚焦在生产模型数据的
    工作上，从而实现 MVC 的充分解耦

注意：视图是最终能看到的一个页面，视图对象是一个对象，不是一回事！
 */
