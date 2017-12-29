import com.Customer;
import com.IBaseDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestWebService {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        IBaseDao myClient = (IBaseDao) applicationContext.getBean("myClent");
        List<Customer> all = myClient.findAll();
        for(Customer customer : all){
            System.out.println(customer.toString());
        }
    }
}
