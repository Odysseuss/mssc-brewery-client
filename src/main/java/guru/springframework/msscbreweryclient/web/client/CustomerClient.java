package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(ignoreUnknownFields = false)
public class CustomerClient {

    public final String URL = "/api/v1/customer/";

    private String apiHost;

    private final RestTemplate restTemplate;


    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CustomerDto getCustomerById(UUID customerId) {
        return restTemplate.getForObject(apiHost + URL + customerId.toString(), CustomerDto.class);
    }

    public URI createCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apiHost + URL, customerDto);
    }

    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        restTemplate.put(apiHost + URL + customerId.toString(), customerDto);
    }

    public void deleteCustomerById(UUID customerId) {
        restTemplate.delete(apiHost + URL + customerId.toString());
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
