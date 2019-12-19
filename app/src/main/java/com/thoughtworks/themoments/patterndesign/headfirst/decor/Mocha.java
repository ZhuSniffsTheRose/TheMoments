package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 * <p>
 * 装饰者，持有待装饰的对象 darkRoast
 * <p>
 * 装饰者 反映 了它所装饰的对象， 所谓的 反映，指的就是两者类型一致。
 * <p>
 * 所以，通过多态，也可以把Mocha所包裹的任何 Beverage 当成 Beverage
 * <p>
 * 装饰者可以在所委托被装饰者的行为前/后,加上自己的行为， 以达到特定的目的.
 * <p>
 * 装饰者，被装饰者，类型一样，巧妙 ===》通过继承达到此目的 ===》装饰者必须能取代被装饰者 ===》那行为从何而来？ 将装饰者与被装饰者组合时，通过组合对象，得到 行为
 * <p>
 * 书中多次提到 「行为」， 不明白。
 * <p>
 * 继承 ===》为了让装饰者和被装饰者类型一样 ===》装饰者必须能替代被装饰者====》 为啥？
 * <p>
 * 那我们的行为是怎么来的？ 就是组合进来的 被装饰者.
 * <p>
 * 也就是说，最原始的 component(这里的 Beverage) 不同的子类有不同实现，那我们装饰者应该做的，就是拿到这个需要改变的方法，也就是这里的行为。
 * 这样理解： 装饰者继承的 Beverage 并不能表示，装饰者就应该实现 Beverage 的抽象方法，装饰者应该实现的，应该是注入的 被装饰者的行为
 * 再深一步理解， 新的行为不是通过继承而来，而是通过改变依赖的darkRoast.
 * <p>
 * <p>
 * 所以我们应该明白， 装饰者这里的继承，只是为了和被装饰者保持一样的类型，而作为装饰者，他的行为只是改变 被装饰者 的行为，便产生了新的行为
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 1;
    }

    @Override
    public String getDescription() {
        return beverage.description + ", Mocha";
    }
}
