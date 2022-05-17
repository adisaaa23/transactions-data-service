package id.saputra.adi.transactionsdataservice.controller;

import id.saputra.adi.transactionsdataservice.domain.dto.TransactionDtoRq;
import id.saputra.adi.transactionsdataservice.services.CustomQueryElasticService;
import id.saputra.adi.transactionsdataservice.services.TransactionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Api(tags = "Transactions Services API", value = "Created by Adi Saputra")
@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private static final String PREFIX_TRACE_ERROR = "Trace error : ";
    
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomQueryElasticService customQueryElasticService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> getAll (@RequestBody TransactionDtoRq transactionDtoRq){
        try {
            return ResponseEntity.ok(transactionService.allTransactions(transactionDtoRq));
        } catch (Exception ex){
            log.error("Happened error when get data transactions. Error {}", ex.getMessage());
            log.trace(PREFIX_TRACE_ERROR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/search/refNo")
    @ResponseBody
    public ResponseEntity<Object> searchByRefNo (@RequestBody TransactionDtoRq transactionDtoRq){
        try {
            return ResponseEntity.ok(transactionService.searchByReferenceNo(transactionDtoRq));
        } catch (Exception ex){
            log.error("Happened error when search data transactions. Error {}", ex.getMessage());
            log.trace(PREFIX_TRACE_ERROR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/search/username")
    @ResponseBody
    public ResponseEntity<Object> searchByUsername (@RequestBody TransactionDtoRq transactionDtoRq){
        try {
            return ResponseEntity.ok(transactionService.searchByUsername(transactionDtoRq));
        } catch (Exception ex){
            log.error("Happened error when search data transactions. Error {}", ex.getMessage());
            log.trace(PREFIX_TRACE_ERROR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/search/productOrAmount")
    @ResponseBody
    public ResponseEntity<Object> searchByProductOrAmount (@RequestBody TransactionDtoRq transactionDtoRq){
        try {
            return ResponseEntity.ok(customQueryElasticService.searchByProduct(transactionDtoRq));
        } catch (Exception ex){
            log.error("Happened error when search data transactions. Error {}", ex.getMessage());
            log.trace(PREFIX_TRACE_ERROR, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
