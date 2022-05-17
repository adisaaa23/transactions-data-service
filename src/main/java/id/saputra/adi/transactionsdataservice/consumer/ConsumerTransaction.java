package id.saputra.adi.transactionsdataservice.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.saputra.adi.transactionsdataservice.domain.dao.TransactionDao;
import id.saputra.adi.transactionsdataservice.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ConsumerTransaction {

    @Autowired
    private TransactionRepository transactionRepository;

    @KafkaListener(topics = "${kafka.topic.transaction}", groupId = "${kafka.group.id}", containerFactory = "containerFactory")
    public void consume(ConsumerRecord<String, Object> consumerRecord) {
        log.info("Start processing record ...");
        if (Objects.nonNull(consumerRecord)) {
            log.debug("consumer record {}", consumerRecord.value());
            ObjectMapper objectMapper = new ObjectMapper();
            TransactionDao transactionDao = objectMapper.convertValue(consumerRecord.value(), TransactionDao.class);
            transactionRepository.save(transactionDao);
            log.info("Processing record save data successfully ...");
        } else {
            log.warn("consumer record is null ...");
        }
    }
}
