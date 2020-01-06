package com.sunyi.prototype.worker;

/**
 * @Copyright: Copyright © 2019 SunYi. All rights reserved.
 * @ClassName: com.sunyi.learn.worker.SendData.java
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
public class SendData  {

    private final String id;

    private String status;

    public SendData(String id, String status) {
        this.id = id;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SendData{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
