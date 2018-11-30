package timer;

public class Timer {
	
	private long currentTime;
	
	public Timer() {
		setCurrentTime(System.currentTimeMillis());
	}
	
	public boolean eventTimer(int timer) {
		if (System.currentTimeMillis() - getCurrentTime() > timer) {
			reset();
			return true;
		}else {
			return false;
		}
	}
	
	public void reset() {
		currentTime = System.currentTimeMillis();
	}
	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}
	
	public boolean isReady(int timer) {
		if (System.currentTimeMillis() - getCurrentTime() > timer) {
			return true;
		}else {
			return false;
		}
	}
}
