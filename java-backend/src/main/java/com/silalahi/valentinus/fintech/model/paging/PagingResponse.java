package com.silalahi.valentinus.fintech.model.paging;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {

	private Integer page;
	private Integer size;
	private Integer totalPage;
	private Integer totalItem;

	@Singular("sort")
	private List<PagingSort> sorts;

}
