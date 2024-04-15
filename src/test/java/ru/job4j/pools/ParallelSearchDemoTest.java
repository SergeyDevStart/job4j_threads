package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelSearchDemoTest {
    @Test
    public void whenSearchThen34() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ParallelSearchDemo<Integer> searchDemo = new ParallelSearchDemo<>(array, 0, array.length, 34);
        assertThat(searchDemo.compute()).isEqualTo(34);
    }

    @Test
    public void whenSearchThen10() {
        String string = "abcdefghijklmnopqrstuvwxyz";
        Character[] array = string.chars()
                .mapToObj(i -> (char) i)
                .toArray(Character[]::new);
        ParallelSearchDemo<Character> searchDemo = new ParallelSearchDemo<>(array, 0, array.length, 'k');
        assertThat(searchDemo.compute()).isEqualTo(10);
    }

    @Test
    public void whenLineSearchThen3() {
        Integer[] array = new Integer[] {1, 2, 4, 34, 54, 6, 7};
        ParallelSearchDemo<Integer> searchDemo = new ParallelSearchDemo<>(array, 0, array.length, 34);
        assertThat(searchDemo.compute()).isEqualTo(3);
    }

    @Test
    public void whenNotElementThenMinus1() {
        Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        ParallelSearchDemo<Integer> searchDemo = new ParallelSearchDemo<>(array, 0, array.length, 50);
        assertThat(searchDemo.compute()).isEqualTo(-1);
    }
}