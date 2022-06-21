package tp_loader.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_loader.handler.LoadHandler;

@Slf4j
@RestController
public class LoaderController {

    @Autowired
    LoadHandler handler;

    @GetMapping("/run")
    void all() {
        handler.execute();
    }
}
