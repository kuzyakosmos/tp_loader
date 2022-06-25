package tp_loader.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp_loader.model.CompanyDto;
import tp_loader.service.CompanyLoader;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoadHandler {

    @Autowired
    CompanyLoader companyLoader;

    public String execute() {
        log.info("Start loading traiding companies");

        try {
            List<String> companies =  companyLoader.load().stream().map(CompanyDto::getSymbol).collect(Collectors.toList());

            log.info("Loaded " + companies.size() + " companies");
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
