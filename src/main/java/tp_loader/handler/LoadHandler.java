package tp_loader.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_loader.service.CompanyLoader;

@Slf4j
@Service
public class LoadHandler {

    @Autowired
    CompanyLoader companyLoader;

    public void execute() {
        log.info("Start loading traiding companies");
        try {
            String companies = companyLoader.load();
            System.out.println(companies);
//            log.info(companies);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
