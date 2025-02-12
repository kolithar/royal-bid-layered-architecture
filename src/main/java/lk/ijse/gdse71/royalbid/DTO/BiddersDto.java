package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BiddersDto {
    private String  BidderId  ;
    private String  BidersName ;
    private String  BiderAddres;
    private String BiddersProfession;
    private LocalDate BidderRegisDate;
}