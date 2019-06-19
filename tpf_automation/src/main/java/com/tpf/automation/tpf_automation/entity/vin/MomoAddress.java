package com.tpf.automation.tpf_automation.entity.vin;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MomoAddress {
        private String addressType;
        private String country;

        private String address1;

        private String address2;

        private String ward;

        private String district;

        private String province;

        private String region;
}
