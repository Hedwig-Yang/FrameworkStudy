import com.atguigu.springpractice2.controller.UserController;
import com.atguigu.springpractice2.dao.UserDao;
import com.atguigu.springpractice2.dao.UserDaoImpl;
import com.atguigu.springpractice2.service.UserService;
import com.atguigu.springpractice2.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:KUN
 * @Data:2021/4/15 16:25
 * @Description: TODO
 * @Version:1.0
 */
public class TestAnnotation {

    @Test
    public void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-annotation.xml");
        UserController uc = ctx.getBean("userController", UserController.class);
        System.out.println(uc);

        UserService us = ctx.getBean("userServiceImpl", UserServiceImpl.class);
        System.out.println(us);

        UserDao ud = ctx.getBean("userDaoImpl", UserDaoImpl.class);
        System.out.println(ud);

    }

    @Test
    public void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-annotation.xml");
        UserController uc = ctx.getBean("userController", UserController.class);
        uc.regist();
    }
}
