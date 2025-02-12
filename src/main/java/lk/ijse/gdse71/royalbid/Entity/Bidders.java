package lk.ijse.gdse71.royalbid.Entity;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bidders {
    private String  BidderId  ;
    private String  BidersName ;
    private String  BiderAddres;
    private String BiddersProfession;
    private LocalDate BidderRegisDate;
}