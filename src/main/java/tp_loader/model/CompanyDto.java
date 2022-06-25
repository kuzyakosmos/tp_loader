package tp_loader.model;

import lombok.Data;

@Data
public class CompanyDto {

    private String symbol;
    private String exchange;
    private String exchangeSuffix;
    private String exchangeName;
    private String exchangeSegment;
    private String exchangeSegmentName;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String isEnabled;
    private String figi;
    private String cik;
    private String lei;
}
