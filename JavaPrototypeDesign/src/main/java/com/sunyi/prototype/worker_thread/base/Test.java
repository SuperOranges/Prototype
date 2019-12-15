package com.sunyi.prototype.worker_thread.base;

import com.sunyi.prototype.worker_thread.base.Production;
import com.sunyi.prototype.worker_thread.base.ProductionChannel;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;

public class Test {

    public static void main(String[] args) {
        final ProductionChannel channel = new ProductionChannel(5);
        AtomicInteger productionNo = new AtomicInteger();

        IntStream.range(1,8).forEach(i-> new Thread(()->{

            final boolean[] condition = {true};
            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
            executorService.scheduleAtFixedRate(() -> condition[0] = false,25,1,TimeUnit.SECONDS);
            while (condition[0]){
                channel.offerProduction(new Production(productionNo.getAndIncrement()));
                try{
                    TimeUnit.SECONDS.sleep(current().nextInt(10));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start());
    }

}
