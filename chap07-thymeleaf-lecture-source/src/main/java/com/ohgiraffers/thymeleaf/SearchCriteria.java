package com.ohgiraffers.thymeleaf;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SearchCriteria {
    private int startPage;
    private int endPage;
    private int currentPage;
}
