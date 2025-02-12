package lk.ijse.gdse71.royalbid.DTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssetsDto {
    private String  AssetsId  ;
    private String  AssetsName ;
    private String AsssetType;
    private String AssetsSpecialties;
    private String CustomerId;
    private String CategoryId;
    private String StartAndEnd;


}