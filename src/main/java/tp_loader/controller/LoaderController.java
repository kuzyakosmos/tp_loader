package tp_loader.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tp_loader.service.LoadService;
import tp_loader.model.StockInfoModel;
import tp_loader.repo.StockInfoRepository;

@Slf4j
@AllArgsConstructor
@RestController
public class LoaderController {

    private final LoadService handler;

    private final StockInfoRepository stockInfoRepository;

    @GetMapping("/execute")
    ResponseEntity<?> execute() {
        handler.execute();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getStockInfo/{id}")
    ResponseEntity<?> result(@PathVariable Integer id) {
        return ResponseEntity.ok(stockInfoRepository.findById(id).orElse(null).toString());
    }

    @GetMapping("/save")
    void save() {
        stockInfoRepository.save(StockInfoModel.builder().companyName("testSave").build());
    }
}
