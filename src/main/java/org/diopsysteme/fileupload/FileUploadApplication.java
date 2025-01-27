package org.diopsysteme.fileupload;

import com.google.cloud.firestore.Firestore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import prog.dependancy.Config.FirebaseConfig;
import prog.dependancy.Exceptions.GlobalException;
import prog.dependancy.Services.Impl.FirestoreService;
import prog.dependancy.Web.Advice.ApiResponseBodyAdvice;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("org.diopsysteme.fileupload.Data.Entities")
@EnableJpaRepositories("org.diopsysteme.fileupload.Data.Repositories")
@ComponentScan(
        basePackages = {"org.diopsysteme.fileupload"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {FirebaseConfig.class, Firestore.class, FirestoreService.class}
        )
)
//@Import({ApiResponseBodyAdvice.class})
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

}
