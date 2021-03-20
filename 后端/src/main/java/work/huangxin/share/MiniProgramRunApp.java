package work.huangxin.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "work.huangxin.share.*")
@MapperScan(basePackages = "work.huangxin.share.mapper")
@EnableTransactionManagement
public class MiniProgramRunApp extends SpringBootServletInitializer {

//    public static void main(String args[]) {
//        SpringApplication.run(MiniProgramRunApp.class, args);
//    }
    /**
     * 以下为Tomcat启动
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MiniProgramRunApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniProgramRunApp.class, args);
    }

}




