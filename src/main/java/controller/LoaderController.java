package controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CompanyLoader;

@Slf4j
@RestController
public class LoaderController {

    @Autowired
    CompanyLoader companyLoader;

    @GetMapping("/run")
    String all() {
        log.info("Пришел запрос на контроллер");
        return companyLoader.load();
    }
}
