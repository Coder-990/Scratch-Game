package com.java.home.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Symbol {

    @JsonProperty("reward_multiplier")
    private BigDecimal rewardMultiplier;
    @JsonProperty("type")
    private String type;
    @JsonProperty("impact")
    private String impact;
    @JsonProperty("extra")
    private Integer extra;

    public Symbol() {
    }

    public Symbol(BigDecimal rewardMultiplier, String type, String impact, Integer extra) {
        this.rewardMultiplier = rewardMultiplier;
        this.type = type;
        this.impact = impact;
        this.extra = extra;
    }

    public BigDecimal getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(BigDecimal rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }
}
