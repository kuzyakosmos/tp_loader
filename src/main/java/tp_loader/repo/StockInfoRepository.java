package tp_loader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tp_loader.model.StockInfoModel;

public interface StockInfoRepository extends JpaRepository<StockInfoModel, Integer> {
}
