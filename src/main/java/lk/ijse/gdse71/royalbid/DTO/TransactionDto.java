package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto {
    private String InvoiceNumber;
    private Double  Amount;
    private Double MinimumBid;
    private Double Profit;
    private String AssetsId;
}