package com.elkady.multithreading.Section6;

public class CriticalSectionExample {


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
        private int items = 0;

        Object lock = new Object();

        public void increment() {
            synchronized(this.lock) {
                items++;

            }
        }

        public void decrement() {
            synchronized(this.lock) {
                items--;
            }
        }

        public synchronized int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
