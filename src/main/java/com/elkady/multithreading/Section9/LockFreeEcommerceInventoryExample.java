package com.elkady.multithreading.Section9;

import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeEcommerceInventoryExample {


    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        IncrementThread incrementThread = new IncrementThread(inventoryCounter);
        DecrementingThread decrementingThread = new DecrementingThread(inventoryCounter);

        incrementThread.start();
        decrementingThread.start();

        incrementThread.join();
        decrementingThread.join();

        System.out.println("Currenty Have " + inventoryCounter.getItems() + "Items");
    }
    public static class IncrementThread extends Thread   {
        private InventoryCounter inventoryCounter;

        public IncrementThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i=0; i< 10000; i++) {
                inventoryCounter.increment();

            }
        }
    }
    public static class DecrementingThread extends Thread   {
        private InventoryCounter inventoryCounter;

        public DecrementingThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i=10000; i > 0; i--) {
                inventoryCounter.decrement();

            }
        }
    }
    private static class InventoryCounter {
        private AtomicInteger items = new AtomicInteger(0);

        public void increment() {
                items.incrementAndGet();
        }

        public void decrement() {
                items.decrementAndGet();
        }

        public int getItems() {
                return items.get();

        }
    }
}
