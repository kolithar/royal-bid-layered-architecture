package lk.ijse.gdse71.royalbid.Entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private String InvoiceNumber;
    private Double  Amount;
    private Double MinimumBid;
    private Double Profit;
    private String AssetsId;
}