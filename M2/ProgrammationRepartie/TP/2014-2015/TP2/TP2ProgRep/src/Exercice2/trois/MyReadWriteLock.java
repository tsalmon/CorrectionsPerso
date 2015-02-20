package Exercice2.trois;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class MyReadWriteLock implements ReadWriteLock {
	private int readersCount = 0;
	private int writersCount = 0;
	private int waitedWriter = 0;
	
	private ReadLock readLock = new ReadLock(this);
	private WriteLock writeLock = new WriteLock(this);
	
	public synchronized void lockRead() throws InterruptedException {
		while (writersCount > 0 || waitedWriter > 0) {
			wait();
		}
		readersCount ++;
	}
	
	public synchronized void unlockRead() throws InterruptedException {
		readersCount --;
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		waitedWriter ++;
		while (readersCount > 0 || writersCount > 0) {
			wait();
		}
		waitedWriter --;
		writersCount ++;
	}
	
	public synchronized void unlockWrite() throws InterruptedException {
		writersCount --;		
		notifyAll();
	}

	@Override
	public Lock readLock() {
		return readLock;
	}

	@Override
	public Lock writeLock() {
		return writeLock;
	}
	
	class ReadLock implements Lock {
		MyReadWriteLock parent;
		
		public ReadLock(MyReadWriteLock _parent) {
			parent = _parent;
		}

		@Override
		public void lock() {//support
			try {
				parent.lockRead();
			} catch (InterruptedException e) {
				
			}
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public Condition newCondition() {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock() {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public void unlock() {//support
			try {
				parent.unlockRead();
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	class WriteLock implements Lock {
		MyReadWriteLock parent;
		
		public WriteLock(MyReadWriteLock _parent) {
			parent = _parent;
		}

		@Override
		public void lock() {
			try {
				parent.lockWrite();
			} catch (InterruptedException e) {
				
			}
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public Condition newCondition() {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock() {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {//not support
			throw new UnsupportedOperationException();
		}

		@Override
		public void unlock() {//support
			try {
				parent.unlockWrite();
			} catch (InterruptedException e) {
				
			}
		}
	}
}



