package com.silalahi.valentinus.fintech.model.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingSort {

	private String field;
	private PagingDirection direction;

}
