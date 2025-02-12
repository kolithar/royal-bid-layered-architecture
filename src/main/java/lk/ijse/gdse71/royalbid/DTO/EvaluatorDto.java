package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EvaluatorDto {
    private String    EvaluatorId;
    private String  EvaluatorName ;
    private String  EvaluatorAddress;
    private String  EvaluatorNumber;
    private String  EvaluatorOk;

    public EvaluatorDto(String invoiceNumber, Double amount, Double minimumBid, Double profit, String assetsId) {

    }
}