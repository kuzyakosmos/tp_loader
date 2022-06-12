package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLoader {

    @Autowired
    List<String> companies;

    public String load() {
        return "Загружено";
    }


}
