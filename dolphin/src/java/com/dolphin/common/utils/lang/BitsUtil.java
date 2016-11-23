
package com.dolphin.common.utils.lang;

public class BitsUtil {

	public static void putBoolean(byte[] bytes, int offset, boolean value) {
		bytes[offset] = (byte) (value ? 1 : 0);
	}

	public static void putChar(byte[] bytes, int offset, char value) {
		bytes[offset + 1] = (byte) (value >>> 0);
		bytes[offset + 0] = (byte) (value >>> 8);
	}

	public static void putShort(byte[] bytes, int offset, short value) {
		bytes[offset + 1] = (byte) (value >>> 0);
		bytes[offset + 0] = (byte) (value >>> 8);
	}

	public static void putInt(byte[] bytes, int offset, int value) {
		bytes[offset + 3] = (byte) (value >>> 0);
		bytes[offset + 2] = (byte) (value >>> 8);
		bytes[offset + 1] = (byte) (value >>> 16);
		bytes[offset + 0] = (byte) (value >>> 24);
	}

	public static void putFloat(byte[] bytes, int offset, float value) {
		int i = Float.floatToIntBits(value);
		bytes[offset + 3] = (byte) (i >>> 0);
		bytes[offset + 2] = (byte) (i >>> 8);
		bytes[offset + 1] = (byte) (i >>> 16);
		bytes[offset + 0] = (byte) (i >>> 24);
	}

	public static void putLong(byte[] bytes, int offset, long value) {
		bytes[offset + 7] = (byte) (value >>> 0);
		bytes[offset + 6] = (byte) (value >>> 8);
		bytes[offset + 5] = (byte) (value >>> 16);
		bytes[offset + 4] = (byte) (value >>> 24);
		bytes[offset + 3] = (byte) (value >>> 32);
		bytes[offset + 2] = (byte) (value >>> 40);
		bytes[offset + 1] = (byte) (value >>> 48);
		bytes[offset + 0] = (byte) (value >>> 56);
	}

	public static void putDouble(byte[] bytes, int offset, double value) {
		long j = Double.doubleToLongBits(value);
		bytes[offset + 7] = (byte) (j >>> 0);
		bytes[offset + 6] = (byte) (j >>> 8);
		bytes[offset + 5] = (byte) (j >>> 16);
		bytes[offset + 4] = (byte) (j >>> 24);
		bytes[offset + 3] = (byte) (j >>> 32);
		bytes[offset + 2] = (byte) (j >>> 40);
		bytes[offset + 1] = (byte) (j >>> 48);
		bytes[offset + 0] = (byte) (j >>> 56);
	}

	public static char getChar(byte[] bytes, int offset) {
		return (char) (((bytes[offset + 1] & 0xFF) << 0) + ((bytes[offset + 0] & 0xFF) << 8));
	}

	public static short getShort(byte[] bytes, int offset) {
		return (short) (((bytes[offset + 1] & 0xFF) << 0) + ((bytes[offset + 0] & 0xFF) << 8));
	}

	public static int getInt(byte[] bytes, int offset) {
		return ((bytes[offset + 3] & 0xFF) << 0)
				+ ((bytes[offset + 2] & 0xFF) << 8)
				+ ((bytes[offset + 1] & 0xFF) << 16)
				+ ((bytes[offset + 0] & 0xFF) << 24);
	}

	public static boolean getBoolean(byte[] bytes, int offset) {
		return bytes[offset] != 0;
	}

	public static float getFloat(byte[] bytes, int offset) {
		int i = ((bytes[offset + 3] & 0xFF) << 0)
				+ ((bytes[offset + 2] & 0xFF) << 8)
				+ ((bytes[offset + 1] & 0xFF) << 16)
				+ ((bytes[offset + 0] & 0xFF) << 24);
		return Float.intBitsToFloat(i);
	}

	public static double getDouble(byte[] bytes, int offset) {
		long j = ((bytes[offset + 7] & 0xFFL) << 0)
				+ ((bytes[offset + 6] & 0xFFL) << 8)
				+ ((bytes[offset + 5] & 0xFFL) << 16)
				+ ((bytes[offset + 4] & 0xFFL) << 24)
				+ ((bytes[offset + 3] & 0xFFL) << 32)
				+ ((bytes[offset + 2] & 0xFFL) << 40)
				+ ((bytes[offset + 1] & 0xFFL) << 48)
				+ ((bytes[offset + 0] & 0xFFL) << 56);
		return Double.longBitsToDouble(j);
	}

	public static long getLong(byte[] bytes, int offset) {
		return ((bytes[offset + 7] & 0xFFL) << 0)
				+ ((bytes[offset + 6] & 0xFFL) << 8)
				+ ((bytes[offset + 5] & 0xFFL) << 16)
				+ ((bytes[offset + 4] & 0xFFL) << 24)
				+ ((bytes[offset + 3] & 0xFFL) << 32)
				+ ((bytes[offset + 2] & 0xFFL) << 40)
				+ ((bytes[offset + 1] & 0xFFL) << 48)
				+ ((bytes[offset + 0] & 0xFFL) << 56);
	}
}
