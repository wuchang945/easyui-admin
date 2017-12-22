package com.feed.ecp.common.util;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadUtil {

	public static Lock LOCK = new ReentrantLock();
}
