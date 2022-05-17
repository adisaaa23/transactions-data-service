package id.saputra.adi.transactionsdataservice.services;

import id.saputra.adi.transactionsdataservice.domain.dao.TransactionDao;
import id.saputra.adi.transactionsdataservice.domain.dto.TransactionDtoRq;
import id.saputra.adi.transactionsdataservice.domain.dto.TransactionDtoRs;
import id.saputra.adi.transactionsdataservice.exception.ApplicationException;
import id.saputra.adi.transactionsdataservice.repository.TransactionRepository;
import id.saputra.adi.transactionsdataservice.util.TransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    private static final String MESSAGE_DATA_NOT_FOUND = "Data not found";
    private static final String MESSAGE_REQ_INCOMPLETE = "Request incomplete";

    @Autowired
    private TransactionRepository transactionRepository;
    
    public Object allTransactions(TransactionDtoRq transactionDtoRq) {
        log.info("Starting get all data Transactions ...");
        if (Objects.isNull(transactionDtoRq)){
            throw new ApplicationException(MESSAGE_REQ_INCOMPLETE, HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = PageRequest.of(transactionDtoRq.getStartPage(),
                transactionDtoRq.getSizePage(), Sort.by("id").descending());
        Page<TransactionDao> transactionDaos = transactionRepository.findAll(pageable);
        log.info("Starting get all data Transactions successfully ...");
        return TransformUtil.transform(transactionDaos, new TransactionDtoRs());
    }

    public Object searchByReferenceNo(TransactionDtoRq transactionDtoRq) {
        log.info("Starting get data Transactions ...");
        if (Objects.isNull(transactionDtoRq)){
            throw new ApplicationException(MESSAGE_REQ_INCOMPLETE, HttpStatus.BAD_REQUEST);
        }
        Optional<TransactionDao> productDaoOptional = transactionRepository.findByReferenceNo(transactionDtoRq.getReferenceNumber());
        if (productDaoOptional.isPresent()){
            TransactionDao productDao = productDaoOptional.get();
            log.info("get data detail Transactions successfully ...");
            return productDao;
        }
        throw new ApplicationException(MESSAGE_DATA_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public Object searchByUsername(TransactionDtoRq transactionDtoRq) {
        log.info("Starting get data Transactions ...");
        if (Objects.isNull(transactionDtoRq)){
            throw new ApplicationException(MESSAGE_REQ_INCOMPLETE, HttpStatus.BAD_REQUEST);
        }
        Pageable pageable = PageRequest.of(transactionDtoRq.getStartPage(),
                transactionDtoRq.getSizePage(), Sort.by("transactionDate").descending());
        Page<TransactionDao> transactionDaos = transactionRepository.findByUsername(transactionDtoRq.getUsername(), pageable);
        log.info("Get all data Transactions successfully ...");
        return TransformUtil.transform(transactionDaos, new TransactionDtoRs());

    }
}
