package tp_loader.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String avgTotalVolume;
    private String calculationPrice;
    private String change;
    private String changePercent;
    private String close;
    private String closeSource;
    private String closeTime;
    private String companyName;
//    private String currency;
//    private String delayedPrice;
//    private String delayedPriceTime;
//    private String extendedChange;
//    private String extendedChangePercent;
//    private String extendedPrice;
//    private String extendedPriceTime;
//    private String high;
//    private String highSource;
//    private String highTime;
//    private String iexAskPrice;
//    private String iexAskSize;
//    private String iexBidPrice;
//    private String iexBidSize;
//    private String iexClose;
//    private String iexCloseTime;
//    private String iexLastUpdated;
//    private String iexMarketPercent;
//    private String iexOpen;
//    private String iexOpenTime;
//    private String iexRealtimePrice;
//    private String iexRealtimeSize;
//    private String iexVolume;
//    private String lastTradeTime;
//    private String latestPrice;
//    private String latestSource;
//    private String latestTime;
//    private String latestUpdate;
//    private String latestVolume;
//    private String low;
//    private String lowSource;
//    private String lowTime;
//    private String marketCap;
//    private String oddLotDelayedPrice;
//    private String oddLotDelayedPriceTime;
//    private String open;
//    private String openTime;
//    private String openSource;
//    private String peRatio;
//    private String previousClose;
//    private String previousVolume;
//    private String primaryExchange;
//    private String symbol;
//    private String volume;
//    private String week52High;
//    private String week52Low;
//    private String ytdChange;
//    private String isUSMarketOpen;
}
