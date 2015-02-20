package Exercice2.deux;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

//ok implement ca1i interface, cai interface nay support mot so chuc nang them chac chan minh ko co kha nang de viet
//nen nhung chuc nang do minh se de rong, ko biet thay co yeu cau implement luon ko, chi implement 4 chuc nang co ban nay gio lam

public class MyReadWriteLock implements ReadWriteLock {
	private int readersCount = 0;
	private int writersCount = 0;
	private int waitedReaders = 0;
	
	private ReadLock readLock = new ReadLock(this);
	private WriteLock writeLock = new WriteLock(this);
	
	synchronized void lockRead() throws InterruptedException {
		waitedReaders ++;
		while (writersCount > 0) {//cai while nay la cho o day, co the cho rat lau co 5-7 nam nhu minh ne:P
			wait();
		}
		waitedReaders --;
		readersCount ++;
	}
	
	synchronized void unlockRead() throws InterruptedException {
		readersCount --;
		notifyAll();
	}
	
	synchronized void lockWrite() throws InterruptedException {
		while (readersCount > 0 || writersCount > 0 || waitedReaders > 0) {
			wait();
		}
		writersCount ++;
	}
	
	synchronized void unlockWrite() throws InterruptedException {
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
		public void lock() {//support goi ham lockRead o ngoai
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



