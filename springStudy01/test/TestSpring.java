import com.atguigu.springpractice1.helloWorld.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Author:KUN
 * @Data:2021/4/15 15:04
 * @Description: 测试Spring
 * @Version:1.0
 */
public class TestSpring {

    @Test
    public void test1() {
        //1、创建IOC容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2、获取person对象
        Person person = (Person) ctx.getBean("person");

        //3、调用方法

        person.sayHello();


    }
}
