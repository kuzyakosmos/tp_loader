package tp_loader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_loader.model.CompanyDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CompanyLoader {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    List<String> companies;

    public List<CompanyDto> load() {

        String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        try {
            ResponseEntity<CompanyDto[]> response = restTemplate.getForEntity(url, CompanyDto[].class);
            return  Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
