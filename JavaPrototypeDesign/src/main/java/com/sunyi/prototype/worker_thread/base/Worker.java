package com.sunyi.prototype.worker_thread.base;

import com.sunyi.prototype.worker_thread.base.Production;
import com.sunyi.prototype.worker_thread.base.ProductionChannel;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

public class Worker extends Thread {

    private final ProductionChannel channel;

    private final static Random random = new Random(System.currentTimeMillis());

    public Worker(String workerName, ProductionChannel channel) {
        super(workerName);
        this.channel = channel;
    }

    @Override
    public void run() {
        final boolean[] condition = {true};
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> condition[0] = false,30,1,TimeUnit.SECONDS);
        while(condition[0]){
            try{
                Production production = channel.takeProduction();
                System.out.println(getName()+"正在处理"+production);
                production.create();
                TimeUnit.SECONDS.sleep(random.nextInt(10));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
