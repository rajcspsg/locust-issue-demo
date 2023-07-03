package com.example;

import com.github.myzhan.locust4j.Locust;

public class Main {
    public static void main(String[] args) {
        Locust locust = Locust.getInstance();
        locust.setMasterHost("127.0.0.1");
        locust.setMasterPort(5558);

        locust.setMaxRPS(1000);
        locust.run(new TaskAlwaysSuccess());
    }

}