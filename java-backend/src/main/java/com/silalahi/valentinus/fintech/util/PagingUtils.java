package com.silalahi.valentinus.fintech.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.silalahi.valentinus.fintech.model.paging.Paging;

public class PagingUtils {

	public static Paging toPaging(Pageable pageable, Page page) {
		return Paging.builder()
				.page(page.getPageable().getPageNumber())
				.size(page.getSize())
				.totalItem((int) page.getTotalElements())
				.totalPage(page.getTotalPages())
				.build();
	}

}
