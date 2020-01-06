package com.sunyi.prototype.worker;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.SendChannel.java
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
public class SendChannel {

    private final SendData[] sendDataQueue;

    private int tail;

    private int head;

    private int total;

    private final Sender[] senders;


    private boolean isAlive;

    public SendChannel() {

        isAlive = true;
        this.sendDataQueue = new SendData[100];
        this.senders = new Sender[3];

        for(int i = 0;i<3;i++){
            senders[i] = new Sender("Sender-" + i,this);
            senders[i].start();
        }

    }

    public void offerSendData(SendData sendData) {
        synchronized (this){
            while (total >= sendDataQueue.length){
                try{
                    this.wait();
                }catch (InterruptedException e){

                }
            }
            sendDataQueue[tail] = sendData;
            tail = (tail+1) % sendDataQueue.length;
            total++;
            this.notifyAll();
        }
    }

    public SendData takeSendData() {
        synchronized (this){
            while(isAlive && total<=0){
                try{
                    this.wait();
                }catch (InterruptedException e){

                }
            }
            if(!isAlive){
                return null;
            }
            SendData sendData = sendDataQueue[head];
            head = (head+1) % sendDataQueue.length;
            total--;
            this.notifyAll();
            return sendData;
        }
    }


    public boolean isAlive() {
        synchronized (this) {
            return isAlive;
        }
    }

    public void setAlive(boolean alive) {
        synchronized (this) {
            isAlive = alive;
            this.notifyAll();
        }
    }

    public int getQueueSize() {
        synchronized (this){
            return total;
        }
    }
}
