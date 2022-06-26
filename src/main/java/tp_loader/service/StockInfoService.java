package tp_loader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_loader.dto.StockInfoDto;

import java.util.List;

@Slf4j
@Service
public class StockInfoService {
    private RestTemplate restTemplate = new RestTemplate();

    public StockInfoDto loadStockInfo(String symbol) {
        String url = "https://sandbox.iexapis.com/stable/stock/" + symbol + "/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        try {
            StockInfoDto response = restTemplate.getForObject(url, StockInfoDto.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void saveStockInfo(List<StockInfoDto> stockInfoDtos) {

    }
}
