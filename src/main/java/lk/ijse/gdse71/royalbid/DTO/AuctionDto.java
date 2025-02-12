package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuctionDto {
    private String  AuctionID;
    private String  LegalMatters;
    private String  AuctionTime;
    private String  Location;
    private String  AssetsId;
}
