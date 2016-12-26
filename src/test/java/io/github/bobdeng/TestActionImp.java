package io.github.bobdeng;

import org.springframework.stereotype.Component;

/**
 * Created by zhiguodeng on 2016/12/26.
 */
@Component
public class TestActionImp implements TestAction {
    public void doAction(){
        System.out.println("TestAction doAction");
    }
}
