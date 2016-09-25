package ukma.eCommerce.util;

public final class TextUtils {
	
	private TextUtils() {
		throw new IllegalAccessError("Shouldn't be called");
	}
	
	public static boolean nullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}

}
