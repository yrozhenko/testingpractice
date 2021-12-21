//package com.solvd.testingpractice.webdriver;
//
//public class ThreadForDriverPool implements Runnable {
//    Thread thread;
//    private WebDriverPool driverPool;
//
//    public ThreadForDriverPool(String threadName, WebDriverPool driverPool) {
//        thread = new Thread(this, threadName);
//        this.driverPool = driverPool;
//        thread.start();
//    }
//
//    @Override
//    public void run() {
//        try {
//            Connection connection = driverPool.retrieve();
//            driverPool.putBack(connection);
//        } catch (SQLException | InterruptedException | TimeoutException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//}
