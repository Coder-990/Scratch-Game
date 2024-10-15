package com.java.home.assignment.service;

import java.math.BigDecimal;

public interface ArgumentsFetchService {
    String getConfigPath(String[] args);

    BigDecimal getBettingAmount(String[] args);
}
