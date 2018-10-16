package com.silalahi.valentinus.fintech.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.silalahi.valentinus.fintech.model.paging.PagingRequest;
import com.silalahi.valentinus.fintech.model.paging.PagingResponse;
import com.silalahi.valentinus.fintech.model.paging.PagingSort;

public class PagingUtils {
	
	public static PagingResponse toPagingResponse(PagingRequest pagingRequest, Page page) {
		return PagingResponse.builder()
				.page(pagingRequest.getPage())
				.size(pagingRequest.getSize())
				.sorts(pagingRequest.getSorts())
				.totalItem((int) page.getTotalElements())
				.totalPage(page.getTotalPages())
				.build();
	}
	
	public static Pageable toPageable(PagingRequest paging) {
		return PageRequest.of(
				paging.getPage() - 1, 
				paging.getSize(),
				Sort.by(toOrderList(paging.getSorts()))
				);
	}

	private static List<Sort.Order> toOrderList(List<PagingSort> sorts) {
		// TODO Auto-generated method stub
		return sorts.stream()
				.map(PagingUtils::toOrder)
				.collect(Collectors.toList());
	}
	
	private static Sort.Order toOrder(PagingSort pagingSort){
		return new Sort.Order(
				Sort.Direction.valueOf(pagingSort.getDirection().name()),
				pagingSort.getField()
				);
	}

}
