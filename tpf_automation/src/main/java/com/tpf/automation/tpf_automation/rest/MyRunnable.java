package com.tpf.automation.tpf_automation.rest;

public class MyRunnable implements Runnable {

   /* public MyRunnable(FptCustomer fptCustomer){
        SeleniumUtils.runAutoNew(fptCustomer);
    }*/
    @Override
    public void run() {

        //testFPT.run_Auto_1("phuongnt","Hcm@12345");
        try {
            Thread.sleep(5000);
            System.out.println("hahahaha");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //SeleniumUtils.runAutoNew();
    }
}
