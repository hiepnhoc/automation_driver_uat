package com.tpf.automation.tpf_automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TpfAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpfAutomationApplication.class, args);
	}

}
