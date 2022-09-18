package tp_loader.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tp_loader.dto.CompanyDto;
import tp_loader.dto.StockInfoDto;
import tp_loader.repo.StockInfoRepository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadService {

    @Value("${batch.size}")
    private int batchSize;

    @Value("${threads.count}")
    private int threadsCount;

    private final CompaniesService companiesService;
    private final StockInfoService stockInfoService;
    private final StockInfoRepository stockInfoRepository;

    public void execute() {
        log.info("Parameter batch.size=" + batchSize);
        log.info("Parameter threads.count=" + threadsCount);
        log.info("Start loading traiding companies");
        //clear table
        try {
            //todo: drop table instead of?
            stockInfoRepository.deleteAll();
            log.info("База данных очищена");
        } catch (Exception ex) {
            log.error("Ошибка в процессе очистки базы данных: {}", ex.getMessage());
        }
        try {
            //load companies
            List<String> companies = companiesService.loadCompanies().stream()
                    .map(CompanyDto::getSymbol).collect(Collectors.toList());
            log.info("Loaded {} companies", companies.size());

            var batches = prepareBatches(companies);

            long start = System.currentTimeMillis();

            ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
            for (List<String> batch : batches) {
                executorService.submit(() -> {
                    List<StockInfoDto> stockInfoDtos =
                            batch.stream().map(stockInfoService::loadStockInfo)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());
                    stockInfoService.saveStockInfo(stockInfoDtos);
                    return "jkbkj";
                });
            }
            executorService.awaitTermination(20, TimeUnit.MINUTES);
            long finish = System.currentTimeMillis();
            log.info("Обработка заняла {}", finish - start);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    private List<List<String>> prepareBatches(List<String> companies) {
        return Lists.partition(companies, batchSize);
    }
}
