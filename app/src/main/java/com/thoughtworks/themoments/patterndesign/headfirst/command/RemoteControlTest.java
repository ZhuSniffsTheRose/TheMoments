package com.thoughtworks.themoments.patterndesign.headfirst.command;

/**
 * Created by Zhu on 2019-12-20
 * 遥控器没有直接拿着灯的对象发命令
 * <p>
 * 而是拿着 命令对象 发命令
 * <p>
 * 命令对象拿着 灯对象.
 * <p>
 * <p>
 * 将请求封装成对象，以便使用不同的请求，队列或者日志来参数化其他对象.
 * <p>
 * <p>
 * 一个命令对象通过在特定接受者上绑定一组动作来封装一个请求。
 * <p>
 * 命令对象只暴露 execute 方法，当次方法被调用，接受者就会进行这些动作
 * <p>
 * 从外边来看，其他对象不知道哪个接受者进行了哪些动作，
 * <p>
 * 只知道如果调用了 execute， 请求的目的就达到了
 * <p>
 * <p>
 * <p>
 * 命令模式： 将运算块打包（一个接受者+一组动作）
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        simpleRemoteControl.setSlot(0, lightOnCommand, lightOffCommand);
        simpleRemoteControl.onButtonWasPressed(0);
        simpleRemoteControl.offButtonWasPressed(0);
    }
}
