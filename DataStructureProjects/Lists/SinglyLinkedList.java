package Lists;



public class SinglyLinkedList implements ISimpleList {

	/**
	 * Node class
	 * 
	 * String val - item stored
	 * String next - the Node it points to
	 */
	private class Node {
		private String val;
		private Node next;

		
		public Node(String val) {
			this.val = val;
			next = null;
		}

		public Node(String val, Node next) {
			this.val = val;
			this.next = next;

		}
	}

	private Node front; //head node
	private Node back; //tail node
	private int size;

	/**
	 * O(1) 
	 * Returns the size of the list
	 * 
	 * @return the list size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * O(1) 
	 * Adds Node to front of the list
	 * 
	 * @param String - Element to be added
	 * @return none
	 */
	@Override
	public void add(String e) {

		if (size == 0) {
			front = new Node(e);
			back = front;
			size++;
		} else {
			front = new Node(e, front); //uses old version of front node as next

			size++;
		}

	}

	/**
	 * O(n) - must traverse list until specified index 
	 * Gets element stored at index parameter. Index starts at 0.
	 * 
	 * @param Int - index of wanted element
	 * 
	 * @return none
	 */
	@Override
	public String get(int index) {

		if(index>size-1) //if index > size-1 node doesn't exist
			return null;
		
		Node temp = front;
		for (int i = 0; i < index; i++)
			temp = temp.next;

		return temp.val;
	}

	/**
	 * O(n) - must traverse list until specified index 
	 * Adds String element to specified position
	 * 
	 * @param 1:
	 *            Int - index where element is to be added
	 * @param 2:
	 *            String - element to be added
	 * 
	 * @return none
	 */
	@Override
	public void add(int index, String e) {
			
		Node temp = front; // Node tracking position of Node previous of index
		for (int i = 1; i < index - 1; i++)
			temp = temp.next;

		Node newTemp = new Node(e, temp.next); // new Node to be added
		temp.next = newTemp;

		size++;

	}

	/**
	 * O(n) - Must traverse and check all elements of specified String 
	 * Removes all instances of specified element and returns number of instances
	 * 
	 * @param :String - element to be removed from list
	 * 
	 * @return Int - number of specified elements removed
	 */
	@Override
	public int remove(String e) {

		int removedCount = 0;
		int orgSize = size; // original size for for loop
		Node temp = front;

		for (int i = 1; i < orgSize - 1; i++)// stops right before the back node

			if (temp.next.val.equals(e)) {

				temp.next = temp.next.next; // skips node
				removedCount++;
				size--;

			} else {
				temp = temp.next;
			}

		if (back.val.equals(e)) {// checks to see if back node needs to be
									// replaced

			back = temp; // reassigns back node
			back.next = null;
			removedCount++;
			size--;
		}

		if (front.val.equals(e)) { // checks front Node for String e

			front = front.next; // Assigns front node to node next to it
			removedCount++;
			size--;
		}

		return removedCount;
	}

	public void toString2() {
		Node temp = front;
		for (int i = 0; i < size; i++) {
			System.out.println("data[" + i + "]=" + temp.val);
			temp = temp.next;
		}
	}

}
