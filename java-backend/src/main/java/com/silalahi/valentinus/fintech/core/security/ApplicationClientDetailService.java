package com.silalahi.valentinus.fintech.core.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.silalahi.valentinus.fintech.entity.ApplicationClient;
import com.silalahi.valentinus.fintech.repository.ApplicationClientRepository;

@Component
public class ApplicationClientDetailService implements ClientDetailsService {

	@Autowired
	private ApplicationClientRepository repository;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		// TODO Auto-generated method stub
		return repository.findByName(clientId)
				.map(this::toClientDetails)
				.orElseThrow(()->new ClientRegistrationException("Client id is not found"));
	}
	
	private ClientDetails toClientDetails(ApplicationClient appClient) {
		BaseClientDetails clientDetails = new BaseClientDetails();
		clientDetails.setClientId(appClient.getName());
		clientDetails.setScope(Arrays.asList("read", "write"));
		clientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token"));;
		return clientDetails;
	}

}
