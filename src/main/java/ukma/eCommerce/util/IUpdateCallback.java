package ukma.eCommerce.util;

import javax.validation.constraints.NotNull;

/**
 * Interface to process long-running operations 
 * asynchronously
 * @author ������
 * */
public interface IUpdateCallback {
	/**
	 * Called before operation execution
	 * */
	default void onPreExecute() {}
	/**
	 * Called when operation was canceled
	 * */
	default void onCancel() {}
	/**
	 * Called when executor finished operation execution successfully
	 * */
	void onSuccess();
	/**
	 * Called in case of operation failure
	 * @param th instance of {@linkplain Throwable} which 
	 * caused failure of the operation
	 * */
	void onFailure(@NotNull Throwable th);

}
