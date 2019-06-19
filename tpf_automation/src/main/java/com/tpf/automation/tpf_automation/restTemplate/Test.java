package com.tpf.automation.tpf_automation.restTemplate;

import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class Test {
    public static void main(String[] args) {
        final String uri ="http://localhost:8080/fpt/test";
        CustomerErrorResponse bodyString = new CustomerErrorResponse();
        bodyString.setField1("1");

        /*-------------SSL self-certificate acceptance------------------*/
        //region SSL self-certificate acceptance
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        //endregion


        //region POST method to add Fpt Documents
        //String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1Mjk2ODU3MX0.Gch23E1djA8UBefbwyrLMHjx2pUvo5gRicUF4ptDWgAC6aYGIWNttPKw5T6oqsB3fZYItTNIwarT4ArDy9tt5A";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", AUTHORIZATION);
        HttpEntity<CustomerErrorResponse> entity = new HttpEntity<CustomerErrorResponse>(bodyString,headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println(result);
        //endregion
    }
}
