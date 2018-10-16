package com.silalahi.valentinus.fintech.core.paging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import com.silalahi.valentinus.fintech.model.paging.PagingDirection;
import com.silalahi.valentinus.fintech.model.paging.PagingRequest;
import com.silalahi.valentinus.fintech.model.paging.PagingSort;
import com.silalahi.valentinus.fintech.properties.PagingProperties;

import reactor.core.publisher.Mono;

public class PagingWebFilterTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private ServerWebExchange serverWebExchange;

	@Mock
	private ServerHttpRequest serverHttpRequest;

	@Mock
	private WebFilterChain webFilterChain;

	private Map<String, Object> attributes = new HashMap<>();
	private PagingProperties pagingProperties = new PagingProperties();
	private MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
	private PagingWebFilter pagingWebFilter;

	@Before
	public void setUp() throws Exception {

		mockWebFilterChain();
		mockServerWebExchange();
		mockServerHttpRequest();

		pagingWebFilter = new PagingWebFilter(pagingProperties);

	}

	private void mockServerHttpRequest() {
		when(serverHttpRequest.getQueryParams()).thenReturn(queryParams);

	}

	private void mockServerWebExchange() {
		when(serverWebExchange.getAttributes()).thenReturn(attributes);
		when(serverWebExchange.getRequest()).thenReturn(serverHttpRequest);

	}

	private void mockWebFilterChain() {
		when(webFilterChain.filter(serverWebExchange)).thenReturn(Mono.empty());

	}
	
	@Test
	public void testNoPaging() {
		pagingWebFilter.filter(serverWebExchange, webFilterChain).block();
		assertNull(attributes.get(PagingWebFilter.PAGING_REQUEST_ATTRIBUTE));
	}
	
	@Test
	public void testPagingOnlySize() {
		queryParams.put(pagingProperties.getKeys().getPage(), Collections.singletonList("100"));

        pagingWebFilter.filter(serverWebExchange, webFilterChain).block();

        PagingRequest pagingRequest = (PagingRequest) attributes.get(PagingWebFilter.PAGING_REQUEST_ATTRIBUTE);
        assertEquals(Integer.valueOf(100), pagingRequest.getPage());
        assertEquals(pagingProperties.getValues().getSize(), pagingRequest.getSize());
        assertEquals(Collections.emptyList(), pagingRequest.getSorts());

	}
	
	@Test
	public void testPageSize() {
		
		queryParams.put(pagingProperties.getKeys().getPage(), Collections.singletonList("100"));
        queryParams.put(pagingProperties.getKeys().getSize(), Collections.singletonList("100"));

        pagingWebFilter.filter(serverWebExchange, webFilterChain).block();

        PagingRequest pagingRequest = (PagingRequest) attributes.get(PagingWebFilter.PAGING_REQUEST_ATTRIBUTE);
        assertEquals(Integer.valueOf(100), pagingRequest.getPage());
        assertEquals(Integer.valueOf(100), pagingRequest.getSize());
        assertEquals(Collections.emptyList(), pagingRequest.getSorts());

	}
	
	@Test
	public void testPageSizeSorts() {
		
		queryParams.put(pagingProperties.getKeys().getPage(), Collections.singletonList("100"));
        queryParams.put(pagingProperties.getKeys().getSize(), Collections.singletonList("100"));
        queryParams.put(pagingProperties.getKeys().getSorts(), Collections.singletonList("firstName:asc,lastName:desc"));

        pagingWebFilter.filter(serverWebExchange, webFilterChain).block();

        PagingRequest pagingRequest = (PagingRequest) attributes.get(PagingWebFilter.PAGING_REQUEST_ATTRIBUTE);
        assertEquals(Integer.valueOf(100), pagingRequest.getPage());
        assertEquals(Integer.valueOf(100), pagingRequest.getSize());
        assertEquals(PagingSort.builder().field("firstName").direction(PagingDirection.ASC).build(), pagingRequest.getSorts().get(0));
        assertEquals(PagingSort.builder().field("lastName").direction(PagingDirection.DESC).build(), pagingRequest.getSorts().get(1));

		
	}

}
