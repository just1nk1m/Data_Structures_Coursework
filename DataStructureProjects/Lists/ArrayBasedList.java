package Lists;



public class ArrayBasedList implements ISimpleList {
	private String[] data = new String[2];
	private int size;

	/**
	 * O(1) returns the size of list
	 * 
	 * @return Int - list size
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * Best/Average Case:O(1) Worst Case: O(n): need to ensureCapacity which is
	 * O(n)
	 * 
	 * Adds element to list
	 * 
	 * @param String
	 *            - element to be added
	 * 
	 * @return none
	 */
	@Override
	public void add(String e) {
		if (size >= data.length) {
			ensureCapacity();
			data[size++] = e;
		} else
			data[size++] = e;

	}

	/**
	 * O(1)
	 * 
	 * Returns element at specified index
	 * 
	 * @param Int
	 *            - index of element
	 * 
	 * @return String - element at specified index
	 */
	@Override
	public String get(int index) {
		return data[index];
	}

	/**
	 * O(n) - have to shift over n elements to make room
	 * 
	 * Adds element at specified index
	 * 
	 * @param1 - Int index where element is to be added
	 * @param2 - String element that will be added
	 * 
	 * @return none
	 */
	@Override
	public void add(int index, String e) {
		if (size == index) // if adding to end of list just does standard add
							// method
			add(e);
		else {
			int count = size;
			if (size >= data.length) { //once array capacity is reached
				ensureCapacity(); 

				for (int i = 0; i < size - index; i++) {
					data[count] = data[count - 1];
					count--;
				}

				data[index] = e;
				size++;

			} else {

				for (int i = 0; i < size - index; i++) {
					data[count] = data[count - 1];
					count--;
				}

				data[index] = e;
				size++;
			}
		}
	}

	/**
	 * O(n) - Goes through list and nulls indexes of specified element and
	 *        copies list to a new list
	 * 
	 * Removes all instances of specified element and returns number of removed
	 * 
	 * @param String
	 *            - String to be removed from list
	 * 
	 * @return Int - number of elements removed
	 */
	@Override
	public int remove(String e) {
		int removedCount = 0;
		int orgSize = size;// original size for the for-loops
		int newListIndex = 0; // index for the new list
		String[] newList = new String[size];

		for (int i = 0; i < orgSize; i++) {
			if (data[i].equals(e)) {

				data[i] = null;
				size--;
				removedCount++;

			}

		}

		for (int i = 0; i < orgSize; i++)
			if (data[i] != null)
				newList[newListIndex++] = data[i];
		data = newList;

		return removedCount;
	}

	/**
	 * O(n) - Must copy all elements(n) to a new array
	 * 
	 * Creates a new array of double size and copies all elements into new array
	 * 
	 * @return none
	 */
	public void ensureCapacity() {
		String[] temp = new String[data.length * 2];
		for (int i = 0; i < data.length; i++)
			temp[i] = data[i];
		data = temp;
	}

}