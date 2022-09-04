package tp_loader.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_loader.dto.StockInfoDto;
import tp_loader.model.StockInfoModel;
import tp_loader.repo.StockInfoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockInfoService {
    private final StockInfoRepository stockInfoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public StockInfoService(StockInfoRepository stockInfoRepository) {
        this.stockInfoRepository = stockInfoRepository;
    }

    public StockInfoDto loadStockInfo(String symbol) {
        String url = "https://sandbox.iexapis.com/stable/stock/" + symbol + "/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";
        try {
            StockInfoDto response = restTemplate.getForObject(url, StockInfoDto.class);
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void saveStockInfo(List<StockInfoDto> stockInfoDtos) {
        List<StockInfoModel> models = stockInfoDtos.stream().map(dto -> (StockInfoModel.builder()
                        .avgTotalVolume(dto.getAvgTotalVolume())
                        .calculationPrice(dto.getCalculationPrice())
                        .change(dto.getChange())
                        .changePercent(dto.getChangePercent())
                        .close(dto.getClose())
                        .closeSource(dto.getCloseSource())
                        .closeTime(dto.getCloseTime())
                        .companyName(dto.getCompanyName())
                        .build()))
                .collect(Collectors.toList());

        stockInfoRepository.saveAll(models);
    }
}
