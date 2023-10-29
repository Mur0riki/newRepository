package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

    interface StockMarket {
        void add(Stock stock);
        void remove(Stock stock);
        Stock mostValuableStock();
    }
    class Stock{
        private int value;
        private String name;
        Stock(int value, String name){
            this.value = value;
            this.name = name;
        }
        public int getValue(){
            return this.value;
        }
    }
    class MosByrzha implements StockMarket{
        PriorityQueue<Stock> stockPriorityQueue;

        public MosByrzha() {
            Comparator<Stock> comparator = (o1, o2) -> (o2.getValue() - o1.getValue());
            stockPriorityQueue = new PriorityQueue<Stock>(comparator);
        }

        @Override
        public void add(Stock stock) {
            stockPriorityQueue.offer(stock);
        }

        @Override
        public void remove(Stock stock) {
            stockPriorityQueue.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return  stockPriorityQueue.poll();
        }
    }
