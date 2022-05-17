package id.saputra.adi.transactionsdataservice.services;

import id.saputra.adi.transactionsdataservice.domain.dao.TransactionDao;
import id.saputra.adi.transactionsdataservice.domain.dto.TransactionDtoRq;
import id.saputra.adi.transactionsdataservice.domain.dto.TransactionDtoRs;
import id.saputra.adi.transactionsdataservice.exception.ApplicationException;
import id.saputra.adi.transactionsdataservice.util.TransformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Slf4j
@Service
public class CustomQueryElasticService {

    private static final String MESSAGE_REQ_INCOMPLETE = "Request incomplete";

    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    public Object searchByProduct(TransactionDtoRq transactionDtoRq){
        log.info("Starting searchByProductOrAmount Transactions ...");
        if (Objects.isNull(transactionDtoRq)){
            throw new ApplicationException(MESSAGE_REQ_INCOMPLETE, HttpStatus.BAD_REQUEST);
        }

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("productCode", transactionDtoRq.getProductCode()))
                .build();

        SearchHits<TransactionDao> articles = elasticsearchTemplate.search(searchQuery, TransactionDao.class, IndexCoordinates.of("idx_transactions"));
        log.info("searchByProductOrAmount Transactions successfully ...");
        return TransformUtil.transform(articles.get(), new TransactionDtoRs());
    }
}
