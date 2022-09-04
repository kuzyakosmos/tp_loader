package tp_loader.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_loader.handler.LoadHandler;
import tp_loader.model.StockInfoModel;
import tp_loader.repo.StockInfoRepository;

@Slf4j
@RestController
public class LoaderController {

    @Autowired
    LoadHandler handler;

    @Autowired
    StockInfoRepository stockInfoRepository;

    @GetMapping("/run")
    String all() {
        return handler.execute();
    }

    @GetMapping("/result")
    String result() {
        return stockInfoRepository.findById(1).get().toString();
    }

    @GetMapping("/save")
    void save() {
        stockInfoRepository.save(StockInfoModel.builder().id(2).companyName("testSave").build());
    }
}
