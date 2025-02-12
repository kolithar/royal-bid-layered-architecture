package lk.ijse.gdse71.royalbid.Entity;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Assets {
    private String  AssetsId  ;
    private String  AssetsName ;
    private String AsssetType;
    private String AssetsSpecialties;
    private String CustomerId;
    private String CategoryId;
    private String StartAndEnd;


}