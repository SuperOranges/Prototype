package com.sunyi.prototype.worker;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.WorkerController.java
 * @SRS:
 * @Description:
 * @version: v1.0.0
 * @author: SunYi
 * @Date: 2020/1/6
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2020/1/6    SunYi           v1.0.0             create
 */

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {

    @RequestMapping(value = "/index")
    public String run(){

        List<SearchTask> st = new ArrayList<>(16);
        st.add(new SearchTask("1","2"));
        st.add(new SearchTask("2","1"));
        st.add(new SearchTask("3","4"));
        st.add(new SearchTask("4","5"));
        st.add(new SearchTask("5","6"));
        st.add(new SearchTask("6","7"));
        st.add(new SearchTask("7","1"));
        st.add(new SearchTask("8","10"));

        List<SendData> sd = new ArrayList<>(16);
        sd.add(new SendData("1","zzz"));
        sd.add(new SendData("2","ssss"));
        sd.add(new SendData("3","cccc"));
        sd.add(new SendData("4","ssss"));
        sd.add(new SendData("5","xxxx"));

        final SendChannel sendChannel = new SendChannel();

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(false).build());
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                sendChannel.setAlive(false);
            }
        },15,TimeUnit.SECONDS);

        executorService.shutdown();

        final SearchChannel searchChannel = new SearchChannel(sendChannel,st);

        for(SendData s:sd){
            sendChannel.offerSendData(s);
        }

        return "sh";
    }

}
