package com.tpf.automation.tpf_automation.restTemplate;

import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.element.finnone.QuitFPTWait;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.WebDriver;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class AutomationStatusUpdate {
    public static void UpdateStatus (CustomerErrorResponse customerErrorResponse, WebDriver driver) {
        try {
            System.out.println("Field0:" + customerErrorResponse.getField0().trim());
            System.out.println("Field1:" + customerErrorResponse.getField1().trim());
            System.out.println("Field6:" + customerErrorResponse.getField6().trim());
            final String uri = "http://10.1.66.27:9000/api/fpt/customers_json/" + "" +
                    //final String uri = "http://localhost:9000/api/fpt/customers_json/" + "" +
                    //final String uri = "http://10.131.21.80:9000/api/momo/customers_json/" + "" +
                    customerErrorResponse.getField0().trim() + "/" +
                    customerErrorResponse.getField1().trim() + "/update_automation_result?access_key=access_key_db&automation_result=" + "" +
                    customerErrorResponse.getField6().trim();

            /*final String uri = "http://10.1.66.27:9000/api/fpt/customer_json/218083/APPL00057624/update_automation_result?automation_result=FAILED_MAIN MENU";*/
            System.out.println(uri);
            //AutomationConstant.userId<>

            //CustomerErrorResponse bodyString = new CustomerErrorResponse();
            //bodyString.setField1("1");

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
            HttpEntity<CustomerErrorResponse> entity = new HttpEntity<CustomerErrorResponse>(customerErrorResponse, headers);
            //System.out.println("Sent get");
            try {
                ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.getMessage();
            }

            System.out.println("result is: ");

            //endregion
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            QuitFPTWait quitFPTWait = new QuitFPTWait(driver, customerErrorResponse);
            System.out.println("Quit test");
            quitFPTWait.QuitFinnOne("QUIT FINNONE", "QUIT FINNONE");
        }
    }
}
