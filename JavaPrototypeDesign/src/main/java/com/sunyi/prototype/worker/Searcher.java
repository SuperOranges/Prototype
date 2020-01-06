package com.sunyi.prototype.worker;

import java.util.Date;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.Searcher.java
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
public class Searcher extends  Thread {

    private final SearchChannel searchChannel;

    private final SendChannel sendChannel;

    public Searcher(String name, SearchChannel searchChannel, SendChannel sendChannel) {
        super(name);
        this.searchChannel = searchChannel;
        this.sendChannel = sendChannel;
    }

    @Override
    public void run() {
        while(searchChannel.getQueueSize()>0){
            SearchTask searchTask = searchChannel.takeJob();
            System.out.println(searchTask.toString());
            SendData sendData = new SendData(getName()+new Date().toString(),"1");
            sendChannel.offerSendData(sendData);
        }
    }
}
