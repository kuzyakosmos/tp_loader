package tp_loader.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_loader.dto.CompanyDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompaniesService {
    private final RestTemplate restTemplate;

    @Value("${iex.api.host}")
    private String iexApiHost;
    @Value("${iex.api.token}")
    private String iexApiToken;

    public List<CompanyDto> loadCompanies() {
        log.info("iexApiHost={}", iexApiHost);
        log.info("iexApiToken={}", iexApiToken);
        try {
            String url = String.format("%s/stable/ref-data/symbols?token=%s", iexApiHost, iexApiToken);
            log.info("URI: {}", url);
            ResponseEntity<CompanyDto[]> response = restTemplate.getForEntity(url, CompanyDto[].class);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
