package com.sunyi.prototype.worker_thread.base;

public abstract class InstructionBook {

    public final void create(){
        this.firstProcess();
        this.secondProcess();
    }


    protected abstract void firstProcess();

    protected abstract void secondProcess();

}
