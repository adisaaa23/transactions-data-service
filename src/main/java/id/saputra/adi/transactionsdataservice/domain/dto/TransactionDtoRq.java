package id.saputra.adi.transactionsdataservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDtoRq implements Serializable {

    private static final long serialVersionUID = -9163964041901941875L;
    private Integer startPage;
    private Integer sizePage;
    private String referenceNumber;
    private String username;
    private String productCode;
}
