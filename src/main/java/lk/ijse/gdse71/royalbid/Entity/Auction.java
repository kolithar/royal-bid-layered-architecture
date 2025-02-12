package lk.ijse.gdse71.royalbid.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Auction {
    private String  AuctionID;
    private String  LegalMatters;
    private String  AuctionTime;
    private String  Location;
    private String  AssetsId;
}
