package com.tpf.automation.tpf_automation.entity.vin;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MomoIncomeDto {
    private String incomeHead;
    private String frequency;
    private String amount;
    private String percentage;
}
