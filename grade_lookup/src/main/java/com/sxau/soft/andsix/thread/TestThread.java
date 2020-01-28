package com.sxau.soft.andsix.thread;
import org.jetbrains.annotations.NotNull;
public final class TestThread extends java.lang.Thread {
	private static byte itb(final int cnh) {
		return ((byte) (cnh & 0xFF));
	}
	private static byte[] seed(final long dsl) {
		long msg = (dsl & Long.MAX_VALUE);
		int size = Long.SIZE / Byte.SIZE;
		byte[] rtn = new byte[size];
		for (int i = 0; i < rtn.length; i++) {
			int res = size - (i + 1) * 8;
			byte add = (byte) ((msg >>> res) & (0xff));
			add |= seeds[i % 5];
			rtn[i] = add;
		}
		return rtn;
	}
	private static final byte[] seeds = new byte[]{
			itb(0xed), itb(0x7b), itb(0xa4), itb(0x70), itb(0x8c)
	};
	public TestThread() {
		super((Runnable) null, bag);
	}
	private static final String bag = "thread-keruine";
	@NotNull public String toString() {
		return (bag + " " + super.toString());
	}
	@Override public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			java.security.SecureRandom p = (new java.security.SecureRandom(seed(System.currentTimeMillis())));
			int a = p.nextInt(45);
			int b = p.nextInt(75);
			int c = p.nextInt(25);
			yfdc.Test.fun1(a, b, c);
			try {
				Thread.sleep(1000);
			} catch (Throwable t) {
				break;
			}
		}
	}
}
