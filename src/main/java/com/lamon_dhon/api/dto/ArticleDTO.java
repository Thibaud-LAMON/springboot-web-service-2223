package com.lamon_dhon.api.dto;

import java.util.List;

public record ArticleDTO(
        Long id,
        String designation,
        Integer quantity,
        Float price,
        List<Long>orderId
) {
}
