package lk.ijse.gdse71.royalbid.Entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String  CustomerId;
    private String  CustomerName;
    private String  CustomersAssetName;
    private String  CustomerAddress;
    private String  CustomerNumber;
}