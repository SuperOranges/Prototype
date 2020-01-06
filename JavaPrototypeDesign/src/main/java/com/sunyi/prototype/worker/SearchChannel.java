package com.sunyi.prototype.worker;

import java.util.List;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.SearchChannel.java
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
public class SearchChannel {


    private final SearchTask[] searchTaskQueue;

    private final Searcher[] searchers;

    private int tail;

    private int head;

    private int total;

    public SearchChannel(SendChannel sendChannel, List<SearchTask> list ) {


        this.searchers = new Searcher[5];
        this.searchTaskQueue = list.toArray(new SearchTask[0]);

        this.total = list.size();
        this.tail = list.size()-1>0?list.size()-1:0;

        for(int i = 0;i<5;i++){
            searchers[i] = new Searcher("Searcher-" + i,this,sendChannel);
            searchers[i].start();
        }
    }

    public void offerJob(SearchTask searchTask) {
        synchronized (this){
            while (total >= searchTaskQueue.length){
                try{
                    this.wait();
                }catch (InterruptedException e){

                }
            }
            searchTaskQueue[tail] = searchTask;
            tail = (tail+1) % searchTaskQueue.length;
            total++;
            this.notifyAll();
        }
    }

    public SearchTask takeJob() {
        synchronized (this){
            while(total<=0){
                try{
                    this.wait();
                }catch (InterruptedException e){

                }
            }
            SearchTask searchTask  = searchTaskQueue[head];
            head = (head+1) % searchTaskQueue.length;
            total--;
            this.notifyAll();
            return searchTask;
        }
    }

    public int getQueueSize() {
        synchronized (this){
            return total;
        }
    }

}
