package kr.or.javacafe.core.manager.queue.receiver;

public class MesasgeListener {

	private MessageHandler cb;
	
	public MesasgeListener(MessageHandler cb) {
		this.cb = cb;
	}
	
	public void start() {
		for (int i=0; i<10; i++) {
			System.out.println("서버작업....");
		}
		System.out.println("서버작업 완료 후 push");
		push();
	}
	
	public void push() {
		cb.onMessage();
	}
}
