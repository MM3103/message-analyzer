package liga.medical.medicalmessageanalyzer.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"liga.medical.medicalmessageanalyzer", "liga.medical.common.service"})
public class MedicalMessageAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalMessageAnalyzerApplication.class, args);
    }
}
