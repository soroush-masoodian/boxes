package com.example.demo1;

/**
 *
 */
public interface StatusBarSubscriber {
    void modelChanged(int created, int deleted, int current);
}
