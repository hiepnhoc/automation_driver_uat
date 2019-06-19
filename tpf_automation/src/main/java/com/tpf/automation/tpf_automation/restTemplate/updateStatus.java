package com.tpf.automation.tpf_automation.restTemplate;

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

public class updateStatus {
    public static void main(String[] args) {
        final String uri ="http://10.131.21.99:3000/pushDataJson";
        String bodyString = "{\n" +
                "  \"custId\": \"37643-adfdf\",\n" +
                "  \"appId\": \"APPL00014402\",\n" +
                "  \"lastName\": \"Tran\",\n" +
                "  \"firstName\": \"Nguyen\",\n" +
                "  \"middleName\": \"Hoang\",\n" +
                "  \"nationalId\": \"123456789\",\n" +
                "  \"photos\": [\n" +
                "    {\n" +
                "      \"documentType\": \"ACCA\",\n" +
                "      \"link\": \"https://link.image.1\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"documentType\": \"Signature\",\n" +
                "      \"link\": \"https://link.image.2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"documentType\": \"Delivery note\",\n" +
                "      \"link\": \"https://link.image.4\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

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

        /*---End--------------*/

        /*-----------POST method to add Fpt Documents------------*/
        //region POST method to add Fpt Documents
        //String AUTHORIZATION = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU1Mjk2ODU3MX0.Gch23E1djA8UBefbwyrLMHjx2pUvo5gRicUF4ptDWgAC6aYGIWNttPKw5T6oqsB3fZYItTNIwarT4ArDy9tt5A";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization", AUTHORIZATION);
        HttpEntity<String> entity = new HttpEntity<String>(bodyString,headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println(result);
        //endregion
        /*---------------END------------------*/
    }
}
