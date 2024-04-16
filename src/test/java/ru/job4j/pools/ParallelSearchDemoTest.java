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
        assertThat(ParallelSearchDemo.search(array, 34)).isEqualTo(34);
    }

    @Test
    public void whenSearchThen10() {
        String string = "abcdefghijklmnopqrstuvwxyz";
        Character[] array = string.chars()
                .mapToObj(i -> (char) i)
                .toArray(Character[]::new);
        assertThat(ParallelSearchDemo.search(array, 'k')).isEqualTo(10);
    }

    @Test
    public void whenLineSearchThen3() {
        Integer[] array = new Integer[] {1, 2, 4, 34, 54, 6, 7};
        assertThat(ParallelSearchDemo.search(array, 34)).isEqualTo(3);
    }

    @Test
    public void whenNotElementThenMinus1() {
        Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        assertThat(ParallelSearchDemo.search(array, 50)).isEqualTo(-1);
    }

    @Test
    public void whenSearchLast() {
        Integer[] array = new Integer[15];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        Integer searchElement = array[array.length - 1];
        assertThat(ParallelSearchDemo.search(array, searchElement)).isEqualTo(14);
    }
}