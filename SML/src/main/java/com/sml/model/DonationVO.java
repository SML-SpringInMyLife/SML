package com.sml.model;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationVO {
    private int id;
    private String merchantUid;
    private String impUid;
    private int amount;
    private String buyerName;
    private String buyerEmail;
    private String buyerTel;
    private Timestamp regDate;
}
