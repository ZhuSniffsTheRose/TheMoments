package com.thoughtworks.themoments.patterndesign.headfirst.decor;

/**
 * Created by Zhu on 2019-12-18
 */
public abstract class BeveragenNegi {
    /**
     * 描述每个子类
     */
    private String description;
    private String milk;
    private String soy;
    private String mocha;
    private String whip;

    abstract String getDescription();

    /**
     * 每个子类都有不同的价格
     * <p>
     * 基类提供 cost 实现，计算加入 调料 价钱， 然后子类调用父类的计算获得调料，子类只需要实现自己的其他费用计算.
     * <p>
     * 问题：如果 只写 cost, 那么所有的子类都会计算，比如 牛奶 价格变了， 所有子类都要进行重复操作的修改牛奶价格， 显然牛奶能统一设置
     * <p>
     * 所以就有了以下的变化，
     * <p>
     * 通过  hasXXX 变量，进行组合. 比如 hasMilk == true, 超类计算当前调料的价格，而子类覆盖过的 cost 会扩展超类的功能，把指定
     * 饮料的类型的价钱也加进来 即 ：
        int cost = super.cost() + 12;
     *
     * 问题：1.如果用户要双倍牛奶？ 2.如果修改调料价格会使我们更改现有代码  3.出现新的调料，就要加上新方法，并改变超类中的 cost 方法
     *
     *
     * 解决：
     *
     * 以饮料为主体，然后运行时以调料来"装饰（decorate）"饮料
     *
     * 摩卡和奶泡深焙 咖啡的制作步骤
     *
     * 1.拿一个深焙咖啡（DarkRoast）对象
     * 2.以摩卡（Mocha）对象装饰它
     * 3.以奶泡（whip）对象装饰它
     * 4.调用 cost, 并依赖委托（delegate）将调料的价钱加上去
     *
     *
     * @return
     */
    public int cost() {
        if (hasMilk()) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean hasMilk() {
        return true;
    }

    public void hasSoy() {
    }

    public void hasMocha() {
    }

    public void hasWhip() {
    }


    public void setMilk(String milk) {
        this.milk = milk;
    }

    public void setSoy(String soy) {
        this.soy = soy;
    }

    public void setMocha(String mocha) {
        this.mocha = mocha;
    }

    public void setWhip(String whip) {
        this.whip = whip;
    }
}
