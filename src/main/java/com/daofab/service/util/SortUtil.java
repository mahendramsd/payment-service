package com.daofab.service.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortUtil {
    public static Sort getSort(String sortBy, String sortOrder) {
        Sort pageableSort= Sort.by(sortBy).ascending();
        if (sortOrder.equals("desc")) {
            pageableSort = Sort.by(sortBy).descending();
        }
        if (sortOrder.equals("asc")) {
            pageableSort = Sort.by(sortBy).ascending();
        }
        return pageableSort;
    }
}
