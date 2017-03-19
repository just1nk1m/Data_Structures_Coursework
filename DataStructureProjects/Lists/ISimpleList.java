package Lists;


public interface ISimpleList {
	/**
	 * 
	 * @return the list size
	 */
	int size();
	
	/**
	 * Add item e to the end of the list
	 * @param e
	 */
	void add(String e);

	/**
	 * Get the element at position index
	 * 
	 * @param index
	 * @return
	 */
	String get(int index);
	
	/**
	 * Add item e at position index
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, String e);

	/**
	 * Remove ALL occurrences of element e from the list and return how many were removed
	 * 
	 * @param e the element to remove
	 * @return how many occurrences of e were removed
	 */
	int remove(String e);
}
