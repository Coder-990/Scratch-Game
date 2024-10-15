package com.java.home.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class WinCombination {

    @JsonProperty("reward_multiplier")
    private BigDecimal rewardMultiplier;

    @JsonProperty("when")
    private String when;

    @JsonProperty("count")
    private int count;

    @JsonProperty("group")
    private String group;

    @JsonProperty("covered_areas")
    private String[][] coveredAreas;

    public WinCombination() {}

    public WinCombination(BigDecimal rewardMultiplier, String when, int count, String group, String[][] coveredAreas) {
        this.rewardMultiplier = rewardMultiplier;
        this.when = when;
        this.count = count;
        this.group = group;
        this.coveredAreas = coveredAreas;
    }

    public BigDecimal getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(BigDecimal rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String[][] getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(String[][] coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
