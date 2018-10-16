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
public class PagingRequest {
	private Integer page;
	private Integer size;
	@Singular
	private List<PagingSort> sorts;
}
