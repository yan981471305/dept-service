package demo.ms.dept;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableFeignClients
@SpringBootApplication()
@PropertySource(value = {"errorcode.properties"}, encoding = "utf-8")
public class DeptServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run( DeptServiceApplication.class, args );
    }


}
