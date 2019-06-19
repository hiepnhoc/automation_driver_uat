package com.tpf.automation.tpf_automation.rest;



import com.tpf.automation.tpf_automation.AutomationConstant;
import com.tpf.automation.tpf_automation.SeleniumUtils;
import com.tpf.automation.tpf_automation.entity.*;
import com.tpf.automation.tpf_automation.error.CustomerErrorResponse;
import com.tpf.automation.tpf_automation.fpt.FptAutoNew;
import com.tpf.automation.tpf_automation.fpt.FptAutoSearchApp;
import com.tpf.automation.tpf_automation.restTemplate.AutomationStatusUpdate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/fpt")
public class FptController {
    @PostMapping("/customers_fpt_test")
    public FptCustomer addCustomer(@RequestBody FptCustomer customer) throws Exception{
        //translate(customer);
        /*String [] username = {null};
        String [] password = {null};
        String [] status = {null};
        if(AutomationConstant.userId.size() == 0) {
            AutomationConstant.initUser();
        }
        for(int i = 0; i< AutomationConstant.userId.size(); i++) {
            if (AutomationConstant.userId.get(i).get(2).equals("false")) {
                username[0] = AutomationConstant.userId.get(i).get(0).toString();
                password[0] = AutomationConstant.userId.get(i).get(1).toString();
                System.out.println(username[0]);
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                try {


                    //executorService.submit(() -> testchoi(customer, username[0], password[0]));

                    executorService.submit(() -> SeleniumUtils.runAutoNew(customer, username[0], password[0]));

                } catch (Exception e) {
                    AutomationConstant.userId.get(i).set(2,"true");
                } finally {
                    executorService.shutdown();
                    AutomationConstant.userId.get(i).set(2,"true");
                }
                AutomationConstant.userId.get(i).set(2,"true");
                break;
            }
            else {
                username[0] = "";
            }
        }
        System.out.println(AutomationConstant.userId);
        if(username[0].equals("") ) {
            AutomationConstant.customerQueue.add(customer);
        }*/
        addData(customer);

        return customer;
    }

    @PostMapping("/customers_fpt_test_queue")
    public FptCustomer testaddCustomer(@RequestBody FptCustomer customer) throws Exception{

        testaddData(customer);

        return customer;
    }
    public void testchoi(FptCustomer customer, String a, String b) {
        try {
            System.out.println("Thread " + a);
            Thread.sleep(5000);
            for(int i = 0; i< AutomationConstant.userId1.size(); i++) {
                if(AutomationConstant.userId1.get(i).get(0).equals(a)) {
                    //System.out.println("Nhay vao");
                    AutomationConstant.userId1.get(i).set(2,"false");
                }
            }
            System.out.println("End thread " + a);
            System.out.println(AutomationConstant.userId1);
            if(AutomationConstant.customerQueue.size() > 0) {
                addData(customer);
                AutomationConstant.customerQueue.remove(0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/customers_fpt_test_new")
    public FptCustomer testCustomer(@RequestBody FptCustomer customer) throws Exception{


        FptAutoNew fptAutoNew = new FptAutoNew();
        //fptAutoNew.FptAutomation(customer);




        //testFPT.run_Auto_1("tpfadmin6","Apple@201811");
        return customer;
    }

    @PostMapping("/customers_fpt_test_search")
    public FptCustomer searchCustomer(@RequestBody FptCustomer customer) throws Exception{

        /*FptAutoNew fptAutoNew = new FptAutoNew();
        fptAutoNew.FptAutomation(customer);*/

        FptAutoSearchApp fptAutoSearchApp = new FptAutoSearchApp();
        fptAutoSearchApp.FptSearch(customer);



        //testFPT.run_Auto_1("tpfadmin6","Apple@201811");
        return customer;
    }
    @PostMapping("/test")
    public CustomerErrorResponse addCustomer(@RequestBody CustomerErrorResponse customerErrorResponse) throws Exception{
        //translate(customer);
        return customerErrorResponse;
    }

    public void addData(FptCustomer customer) {
        //translate(customer);
        String [] username = {null};
        String [] password = {null};
        String [] status = {null};
        if(AutomationConstant.userId.size() == 0) {
            AutomationConstant.initUser();
        }
        for(int i = 0; i< AutomationConstant.userId.size(); i++) {
            if (AutomationConstant.userId.get(i).get(2).equals("false")) {
                username[0] = AutomationConstant.userId.get(i).get(0).toString();
                password[0] = AutomationConstant.userId.get(i).get(1).toString();
                //System.out.println(username[0]);
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                try {


                    //executorService.submit(() -> testchoi(customer, username[0], password[0]));

                    executorService.submit(() -> SeleniumUtils.runAutoNew(customer, username[0], password[0]));

                } catch (Exception e) {
                    AutomationConstant.userId.get(i).set(2,"true");
                } finally {
                    executorService.shutdown();
                    AutomationConstant.userId.get(i).set(2,"true");
                }
                AutomationConstant.userId.get(i).set(2,"true");
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
            AutomationConstant.customerQueue.add(customer);
            System.out.println(AutomationConstant.customerQueue.size());
        }
    }

    public void testaddData(FptCustomer customer) {
        //translate(customer);
        String [] username = {null};
        String [] password = {null};
        String [] status = {null};
        if(AutomationConstant.userId1.size() == 0) {
            AutomationConstant.initUser1();
        }
        for(int i = 0; i< AutomationConstant.userId1.size(); i++) {
            if (AutomationConstant.userId1.get(i).get(2).equals("false")) {
                username[0] = AutomationConstant.userId1.get(i).get(0).toString();
                password[0] = AutomationConstant.userId1.get(i).get(1).toString();
                //System.out.println(username[0]);
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                try {


                    executorService.submit(() -> testchoi(customer, username[0], password[0]));

                    //executorService.submit(() -> SeleniumUtils.runAutoNew(customer, username[0], password[0]));

                } catch (Exception e) {
                    AutomationConstant.userId1.get(i).set(2,"true");
                } finally {
                    executorService.shutdown();
                    AutomationConstant.userId1.get(i).set(2,"true");
                }
                AutomationConstant.userId1.get(i).set(2,"true");
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
            AutomationConstant.customerQueue.add(customer);
            System.out.println(AutomationConstant.customerQueue.size());
        }
    }

        //endregion
        

}
