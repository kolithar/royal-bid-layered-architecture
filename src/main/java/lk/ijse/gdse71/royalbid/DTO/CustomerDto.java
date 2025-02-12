package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private String  CustomerId;
    private String  CustomerName;
    private String  CustomersAssetName;
    private String  CustomerAddress;
    private String  CustomerNumber;
}