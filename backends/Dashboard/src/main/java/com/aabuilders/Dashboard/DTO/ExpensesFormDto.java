package com.aabuilders.Dashboard.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpensesFormDto {
    private String accountType;
    @JsonProperty("eno")
    private int ENo;
    private LocalDate date;
    private String siteName;
    private String vendor;
    private String quantity;
    private String contractor;
    private int amount;
    private String category;
    private String comments;
    private String machineTools;
    private String billCopyUrl;
}
