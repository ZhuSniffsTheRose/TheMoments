package com.thoughtworks.themoments.patterndesign.builder;

/**
 * Created by Zhu on 2019-12-11
 */
public class Computer {

    private String cpu;

    private String screen;

    private String key;


    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void setKey(String key) {
        this.key = key;
    }


    static class BuilderComputer {
        private Computer computer = new Computer();

        private String cpu;

        private String screen;

        private String key;

        public BuilderComputer buildCPU(String cpu) {
            computer.setCpu(cpu);
            return this;
        }

        public BuilderComputer buildScreen(String screen) {
            computer.setScreen(screen);
            return this;
        }


        public BuilderComputer buildKey(String key) {
            computer.setKey(key);
            return this;
        }


        public Computer build() {
            return computer;
        }
    }
}
