
package com.tpf.automation.tpf_automation.entity.vin;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "nextEventOffset",
    "referenceId",
    "requestId",
    "totalEvents"
})
public class MomoDTO {

    @JsonProperty("data")
    private MomoData data;
    @JsonProperty("nextEventOffset")
    private Integer nextEventOffset;
    @JsonProperty("referenceId")
    private String referenceId;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("totalEvents")
    private Integer totalEvents;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data")
    public MomoData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(MomoData data) {
        this.data = data;
    }

    @JsonProperty("nextEventOffset")
    public Integer getNextEventOffset() {
        return nextEventOffset;
    }

    @JsonProperty("nextEventOffset")
    public void setNextEventOffset(Integer nextEventOffset) {
        this.nextEventOffset = nextEventOffset;
    }

    @JsonProperty("referenceId")
    public String getReferenceId() {
        return referenceId;
    }

    @JsonProperty("referenceId")
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    @JsonProperty("requestId")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonProperty("totalEvents")
    public Integer getTotalEvents() {
        return totalEvents;
    }

    @JsonProperty("totalEvents")
    public void setTotalEvents(Integer totalEvents) {
        this.totalEvents = totalEvents;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
