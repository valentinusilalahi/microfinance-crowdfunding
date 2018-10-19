package com.silalahi.valentinus.fintech.model.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paging {

	private Integer page;
	private Integer size;
	private Integer totalPage;
	private Integer totalItem;

}
