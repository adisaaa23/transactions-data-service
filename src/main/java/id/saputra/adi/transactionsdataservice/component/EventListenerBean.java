package id.saputra.adi.transactionsdataservice.component;

import id.saputra.adi.transactionsdataservice.domain.dao.TransactionDao;
import id.saputra.adi.transactionsdataservice.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Slf4j
public class EventListenerBean {
    @Autowired
    private TransactionRepository transactionRepository;
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event){
        /* Initial Data Products */
        log.info("Prepare insert initiate transactions data");
        BigInteger amount = BigInteger.valueOf(100000);
        int counterSum = 1;
        for (int i = 0; i <= 15; i++){
            if (counterSum == 3){
                amount = amount.add(BigInteger.valueOf(50000));
            }
            counterSum++;
            TransactionDao transactionDao = TransactionDao.builder()
                    .referenceNo("001" + i)
                    .username("adisaputra")
                    .riskProfile("1")
                    .productCode("00A" + i)
                    .productName("Product AAA" + i)
                    .amount(amount.toString())
                    .transactionStatus("SUCCESS")
                    .transactionDesc("SUCCESS")
                    .build();
            transactionRepository.save(transactionDao);
        }
        log.info("Successfully insert initiate products data");
    }
}
