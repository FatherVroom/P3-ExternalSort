// Min-heap implementation by Patrick Sullivan, based on OpenDSA Heap code
// Can use `java -ea` (Java's VM arguments) to Enable Assertions
// These assertions will check valid heap positions
public class MinHeap<T extends Comparable<T>> {
	private T[] heap; // Pointer to the heap array
	private int capacity; // Maximum size of the heap
	private int n; // Number of things currently in heap

	// Constructor supporting preloading of heap contents
	public MinHeap(T[] h, int heapSize, int capacity) {
		assert capacity <= h.length: "capacity is beyond array limits";
		assert heapSize <= capacity: "Heap size is beyond max";
		heap = h;
		n = heapSize;
		this.capacity = capacity;
		buildHeap();
	}


	// Return position for left child of pos
	public static int leftChild(int parentPosition) {
		return 2 * parentPosition + 1;
	}


	// Return position for right child of pos
	public static int rightChild(int parentPosition) {
		return 2 * parentPosition + 2;
	}


	// Return position for the parent of pos
	public static int parent(int childPosition) {
		return (childPosition - 1) / 2;
	}


	// Forcefully changes the heap size. May require build-heap afterwards
	public void setHeapSize(int newSize) {
		n = newSize;
	}


	// Return current size of the heap
	public int heapSize() {
		return n;
	}


	// Return true if pos a leaf position, false otherwise
	public boolean isLeaf(int pos) {
		return (n / 2 <= pos) && (pos < n);
	}


	// Insert val into heap
	public boolean insert(T key) {
	    if (n >= capacity) {
	        return false;
	    }
		heap[n] = key;
		n++;
		siftUp(n - 1);
		return true;
	} 


	// Organize contents of array to satisfy the heap structure
	public void buildHeap() {
		for (int i = parent(n - 1); i >= 0; i--) {
			siftDown(i);
		}
	}


	// Moves an element down to its correct place
	public void siftDown(int pos) {
		assert (0 <= pos && pos < n) : "Invalid heap position";
		while (!isLeaf(pos)) {
			int child = leftChild(pos);
			if ((child + 1 < n) && isLessThan(child + 1, child)) {
				child = child + 1; // child is now index with the smaller value
			}
			if (!isLessThan(child, pos)) {
				return; // stop early
			}
			swap(pos, child);
			pos = child; // keep sifting down
		}
	}


	// Moves an element up to its correct place
	public void siftUp(int pos) {
		assert (0 <= pos && pos < n) : "Invalid heap position";
		while (pos > 0) {
			int parent = parent(pos);
			if (isLessThan(parent, pos)) {
				return; // stop early
			}
			swap(pos, parent);
			pos = parent; // keep sifting up
		}
	}


	// Remove and return minimum value
	public T removeMin() {
		assert n > 0 : "Heap is empty; cannot remove";
		n--;
		if (n > 0) {
    		swap(0, n); // Swap minimum with last value
    		siftDown(0); // Put new heap root val in correct place
		}
		return heap[n];
	}


	// Remove and return element at specified position
	public T remove(int pos) {
		assert (0 <= pos && pos < n) : "Invalid heap position";
		n--;
		if (n > 0) {
			swap(pos, n); // Swap with last value
			update(pos); // Move other value to correct position
		}
		return heap[n];
	}


	// Modify the value at the given position
	public void modify(int pos, T newVal) {
		assert (0 <= pos && pos < n) : "Invalid heap position";
		heap[pos] = newVal;
		update(pos);
	}


	// The value at pos has been changed, restore the heap property
	private void update(int pos) {
		siftUp(pos); // priority goes up
		siftDown(pos); // unimportant goes down
	}


	// swaps the elements at two positions
	private void swap(int pos1, int pos2) {
		T temp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = temp;
	}


	// does fundamental comparison used for checking heap validity
	private boolean isLessThan(int pos1, int pos2) {
		return heap[pos1].compareTo(heap[pos2]) < 0;
	}

}
