package id.purnama.qris.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantAccountInformation {

    private String globallyUniqueIdentifier;
    private Integer personalAccountNumber;
    private String merchantId;
    private MerchantCriteria criteria;

}
