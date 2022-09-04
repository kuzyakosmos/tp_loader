package tp_loader.handler;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tp_loader.dto.CompanyDto;
import tp_loader.dto.StockInfoDto;
import tp_loader.service.CompaniesService;
import tp_loader.service.StockInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LoadHandler {

    @Value("${batch.size}")
    private int batchSize;

    @Value("${threads.count}")
    private int threadsCount;

    @Autowired
    CompaniesService companiesService;

    @Autowired
    StockInfoService stockInfoService;


    {
        log.info("Parameter batch.size=" + batchSize);
        log.info("Parameter threads.count=" + threadsCount);
    }

    public String execute() {
        log.info("Start loading traiding companies");

        try {
            //Сначала загружаем компании
            List<String> companies = companiesService.loadCompanies().stream()
                    .map(CompanyDto::getSymbol).collect(Collectors.toList());
            log.info("Loaded {} companies", companies.size());

            //сделать метод для записи в БД одного батча, пока без многопоточки

            var batches = prepareBatches(companies);

            List<StockInfoDto> stockInfos = new ArrayList<>();
            batches.get(0).forEach(s -> stockInfos.add(stockInfoService.loadStockInfo(s)));
            stockInfoService.saveStockInfo(stockInfos);


        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    private List<List<String>> prepareBatches(List<String> companies) {
        return Lists.partition(companies, 10);
    }
}
