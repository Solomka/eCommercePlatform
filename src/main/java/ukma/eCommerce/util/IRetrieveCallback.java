package ukma.eCommerce.util;

import javax.validation.constraints.NotNull;

/**
 * Interface to process long-running operations
 * asynchronously
 * @author ������
 * @deprecated use rx observable instead
 * */
@Deprecated
public interface IRetrieveCallback <Result> {
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
	 * @param result operation execution result
	 * */
	void onResult(@NotNull Result result);
	/**
	 * Called in case of operation failure
	 * @param th instance of {@linkplain Throwable} which
	 * caused failure of the operation
	 * */
	void onFailure(@NotNull Throwable th);

}