package com.telecom.springmvc.handler;

import com.telecom.springmvc.beans.Department;
import com.telecom.springmvc.beans.Employee;
import com.telecom.springmvc.dao.DepartmentDao;
import com.telecom.springmvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.Request;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:KUN
 * @Data:2021/5/10 15:27
 * @Description:Handler处理CRUD请求
 * @Version:1.0
 */
@Controller
public class RestCrudHandler {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;


    /**
     * 显示所有员工信息列表
     */
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public String listAllEmps(Map<String,Object> map){
        Collection<Employee> emps = employeeDao.getAll();
        map.put("emps",emps);
        return "list";

    }

    /**
     * 添加功能，去往添加页面
     * 添加页面需要部门数据
     */
    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    public String toAddPage(Map<String,Object> map){
        //页面显示部门下拉列表的数据
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts",depts);

        //构造页面中生成单选框的数据
        Map<String,String> genders = new HashMap<>();
        genders.put("0","女");
        genders.put("1","男");
        map.put("genders",genders);

        //设置页面需要回显的数据
        map.put("employee",new Employee());
        return "input";
    }

    /**
     * 添加功能：具体的添加
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String addEmp(Employee employee){
        //添加员工
        employeeDao.save(employee);
        //为了回到列表页面：重定向到显示所有员工的请求（不要进行对显示信息方法的调用）
        return "redirect:/emps";
    }

    /**
     * 删除功能
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id")Integer id){
        System.out.println("到此一游");
        //删除员工
        employeeDao.delete(id);
        //重定向到列表
        return "redirect:/emps";
    }

    /**
     * 修改功能：去往修改页面
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    public String toUpdatePage(@PathVariable("id")Integer id,Map<String,Object> map){
        //查询要修改的员工信息
        Employee employee = employeeDao.get(id);
        //使用employee作为key绑定数据，满足SpringMVC的form表单数据回显的要求
        map.put("employee",employee);
        //页面显示部门下拉列表的数据
        Collection<Department> depts = departmentDao.getDepartments();
        map.put("depts",depts);
        //构造页面中生成单选框的数据
        Map<String,String> genders = new HashMap<>();
        genders.put("0","女");
        genders.put("1","男");
        map.put("genders",genders);
        //去往修改页面
        return "input";
    }

    /**
     * 具体的修改操作
     */
    @RequestMapping(value = "/emp",method=RequestMethod.PUT)
    public String updateEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

}
