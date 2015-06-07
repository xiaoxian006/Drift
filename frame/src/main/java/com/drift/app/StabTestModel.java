package com.drift.app;

import com.drift.frame.TestModel;

/**
 * Created by Ray on 15/6/5.
 */
public abstract class StabTestModel extends TestModel {

    // 稳定需要的qps
    public abstract int setTimeUnit();

    @Override
    public void post_invoke() {
        if (super.getTimer().costTime() < setTimeUnit()) {
            try {
                Thread.sleep(setTimeUnit() - super.getTimer().costTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
