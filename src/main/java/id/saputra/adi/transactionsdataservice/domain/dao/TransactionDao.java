package id.saputra.adi.transactionsdataservice.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

import static org.springframework.data.elasticsearch.annotations.FieldType.Keyword;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "idx_transactions")
public class TransactionDao implements Serializable {

    private static final long serialVersionUID = 1421236295854313607L;

    @Id
    private Integer id;
    @Field(type = Keyword)
    private String referenceNo;
    @Field(type = Keyword)
    private String username;
    private String riskProfile;
    @Field(type = Keyword)
    private String productCode;
    private String productName;
    private String amount;
    private String transactionStatus;
    private String transactionDesc;

}
