package Exercice2.trois;

public class Data {
	MyReadWriteLock readWriteLock = new MyReadWriteLock();
	
	void read(int id) {		
		System.out.println("Read " + id + " request lock");
		readWriteLock.readLock().lock();
		try {
			System.out.println("Read " + id + " gaint lock");
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
			}
		} finally {
			readWriteLock.readLock().unlock();
			System.out.println("Read " + id + " release lock");
		}
	}
	
	void write(int id) {
		System.out.println("Write " + id + " request lock");
		readWriteLock.writeLock().lock();
		try {
			System.out.println("Write " + id + " gaint lock");
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
			}
		} finally {
			readWriteLock.writeLock().unlock();
			System.out.println("Write " + id + " release lock");
		}
	}
}
