package Exercice1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class File {
	final Lock lock = new ReentrantLock();
	final Condition readyToGet = lock.newCondition();
	final Condition readyToPut = lock.newCondition();
	private Integer contents[] = new Integer [3];
	private int head = 0;
	private int tail = 0;
	private int count = 0;

	public int get() {
		lock.lock();
		try {
			while (count == 0) {//empty
				try {
					readyToGet.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count--;
			Integer result = contents[head];
			head = (head + 1) % contents.length;
			readyToPut.signal();
			return result;
		} finally {
			lock.unlock();
		}
	}

	public void put(Integer value) {
		lock.lock();
		try {
			while (count == contents.length) {//full
				try {
					readyToPut.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			contents[tail] = value;
			tail = (tail + 1) % contents.length;
			count++;
			readyToGet.signal();
		} finally {
			lock.unlock();
		}
	}
}
