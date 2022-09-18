package tp_loader.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_loader.dto.StockInfoDto;
import tp_loader.model.StockInfoModel;
import tp_loader.repo.StockInfoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class StockInfoService {

    @Value("${iex.api.host}")
    private String iexApiHost;
    @Value("${iex.api.token}")
    private String iexApiToken;
    private final StockInfoRepository stockInfoRepository;

    private final RestTemplate restTemplate;

    public StockInfoDto loadStockInfo(String symbol) {
        String url = String.format("%s/stable/stock/%s/quote?token=%s", iexApiHost, symbol, iexApiToken);
        try {
            return restTemplate.getForObject(url, StockInfoDto.class);
        } catch (Exception e) {
            log.error("Error loading info for {} company: {}", symbol, e.getMessage());
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
        try {
            stockInfoRepository.saveAll(models);
            log.info("Saved {} companies", models.size());
        } catch (Exception ex) {
            log.error("Error with saving batch to db: {}", ex.getMessage());
        }
    }
}
