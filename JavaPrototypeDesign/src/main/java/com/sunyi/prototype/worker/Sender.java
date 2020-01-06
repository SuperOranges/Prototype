package com.sunyi.prototype.worker;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.Sender.java
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
public class Sender extends Thread {

    private final SendChannel sendChannel;

    public Sender(String name, SendChannel sendChannel) {
        super(name);
        this.sendChannel = sendChannel;
    }

    @Override
    public void run() {

        while(sendChannel.isAlive()){
            SendData sendData = sendChannel.takeSendData();
            if(sendData !=null){
                System.out.println(sendData.toString());
            }

        }
    }
}
