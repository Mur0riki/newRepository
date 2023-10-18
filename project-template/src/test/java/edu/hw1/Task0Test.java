package edu.hw1;

import org.junit.jupiter.api.Test;

class Task0Test {
    @Test
    void name() {
        Task0 task = new Task0();
        org.assertj.core.api.Assertions.assertThatNoException().isThrownBy(task::sayHello);
    }

}
