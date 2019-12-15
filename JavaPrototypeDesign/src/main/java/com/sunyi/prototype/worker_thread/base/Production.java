package com.sunyi.prototype.worker_thread.base;

import com.sunyi.prototype.worker_thread.base.InstructionBook;

/**
 * 产品模型 /可替换成数据
 */
public class Production extends InstructionBook {

    private final int prodId;

    public Production(int prodId) {
        this.prodId = prodId;
    }

    @Override
    protected void firstProcess() {
        System.out.println("第一步操作数据："+prodId);
    }

    @Override
    protected void secondProcess() {
        System.out.println("第二步操作数据："+prodId);
    }

    @Override
    public String toString() {
        return "Production{" +
                "prodId=" + prodId +
                '}';
    }
}
