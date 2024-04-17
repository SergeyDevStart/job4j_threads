package ru.job4j.completable;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    private static void iWork() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            System.out.println("Вы: Я работаю");
            Thread.sleep(1000);
            count++;
        }
    }

    public static CompletableFuture<Void> goToTrash() {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Сын: Мам/Пам, я пошел выносить мусор");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Сын: Мам/Пап, я вернулся!");
        });
    }

    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Сын: Мам/Пам, я пошел в магазин");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Сын: Мам/Пап, я купил " + product);
            return product;
        });
    }

    public static void thenRunExample() throws Exception {
        CompletableFuture<Void> result = goToTrash();
        result.thenRun(() -> {
            int count = 0;
            while (count < 3) {
                System.out.println("Сын: я мою руки");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }
            System.out.println("Сын: Я помыл руки");
        });
        iWork();
    }

    public static void thenAcceptExample() throws Exception {
        CompletableFuture<String> buy = buyProduct("Молоко");
        buy.thenAccept((product) -> System.out.println("Сын: Я убрал " + product + " в холодильник"));
        iWork();
        System.out.println("Куплено: " + buy.get());
    }

    public static void main(String[] args) throws Exception {
        thenAcceptExample();
    }
}
