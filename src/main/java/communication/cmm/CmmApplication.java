package communication.cmm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "communication.cmm.mapper")
public class CmmApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmmApplication.class, args);
    }

}
