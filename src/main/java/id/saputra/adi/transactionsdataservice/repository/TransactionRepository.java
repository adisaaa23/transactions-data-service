package id.saputra.adi.transactionsdataservice.repository;

import id.saputra.adi.transactionsdataservice.domain.dao.TransactionDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<TransactionDao, Integer> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"referenceNo\": \"?0\"}}]}}")
    Optional<TransactionDao> findByReferenceNo(String referenceNo);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"username\": \"?0\"}}]}}")
    Page<TransactionDao> findByUsername(String username, Pageable pageable);

}
