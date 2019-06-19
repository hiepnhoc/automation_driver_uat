package com.tpf.automation.tpf_automation.rest;

import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.entity.FptCustomer;
import com.tpf.automation.tpf_automation.entity.vin.MomoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/momo")
public class MomoController {

    @PostMapping("/customers")
    public MomoDTO addCustomer(@RequestBody MomoDTO momoDTO) throws Exception{

        addData(momoDTO);

        return momoDTO;
    }

    public void addData(MomoDTO customer) {
        //translate(customer);
        String [] username = {null};
        String [] password = {null};
        String [] status = {null};
        if(AutomationConstant.userIdMomo.size() == 0) {
            AutomationConstant.initUserMomo();
        }
        for(int i = 0; i< AutomationConstant.userIdMomo.size(); i++) {
            if (AutomationConstant.userIdMomo.get(i).get(2).equals("false")) {
                username[0] = AutomationConstant.userIdMomo.get(i).get(0).toString();
                password[0] = AutomationConstant.userIdMomo.get(i).get(1).toString();
                //System.out.println(username[0]);
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                try {
                    executorService.submit(() -> SeleniumUtils.runMomo(customer, username[0], password[0]));
                } catch (Exception e) {
                    AutomationConstant.userIdMomo.get(i).set(2,"true");
                } finally {
                    executorService.shutdown();
                    AutomationConstant.userIdMomo.get(i).set(2,"true");
                }
                AutomationConstant.userIdMomo.get(i).set(2,"true");
                break;
            }
            else {
                username[0] = "";
            }
        }
        //System.out.println(AutomationConstant.userId1);
        if(username[0].equals("") ) {
            System.out.println("full queue");
            //AutomationStatusUpdate.UpdateStatus();
            AutomationConstant.customerMomoQueue.add(customer);
            System.out.println(AutomationConstant.customerMomoQueue.size());
        }
    }
}
