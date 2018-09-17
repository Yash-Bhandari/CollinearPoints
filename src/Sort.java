import java.util.Comparator;

public class Sort {
	
	public static void mergeSort(Object[] array, Comparator<Object> c) {
		Object[] aux = array.clone();
		int subdivision = 1;
		while (subdivision < array.length) {
			subdivision = subdivision * 2;
			for (int i = 0; i < array.length; i += subdivision) {
				merge(array, aux, i, Math.min(i + subdivision / 2 - 1, array.length - 1),
						Math.min(i + subdivision - 1, array.length - 1));
			}
		}
	}

	// bottom up implementation of pure mergesort
	public static void mergeSort(Integer[] array) {
		Integer[] aux = array.clone();
		int subdivision = 1;
		while (subdivision < array.length) {
			subdivision = subdivision * 2;
			for (int i = 0; i < array.length; i += subdivision) {
				merge(array, aux, i, Math.min(i + subdivision / 2 - 1, array.length - 1),
						Math.min(i + subdivision - 1, array.length - 1));
			}
		}
	}
	
	private static void merge(Object[] array, Object[] aux, int low, int med, int high) {
		int left = low;
		int right = med + 1;
		int i = low;
		while (left <= med || right <= high) {
			if (left > med)
				array[i++] = aux[right++];
			else if (right > high)
				array[i++] = aux[left++];
			else if (aux[left] <= aux[right])
				array[i++] = aux[left++];
			else
				array[i++] = aux[right++];
		}
		copy(aux, array, low, high);
	}

	private static void copy(Integer[] array, Integer[] aux, int low, int high) {
		for (int i = low; i <= high; i++) {
			array[i] = aux[i];
		}
	}

	public static void selectionSort(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			Integer lowest = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < array[lowest]) {
					lowest = j;
				}
			}
			swap(array, i, lowest);
		}
	}

	public static void insertionSort(Integer[] array) {
		for (int i = 0; i < array.length; i++) {
			int current = i;
			for (int j = i; j >= 0; j--) {
				if (array[current] < array[j]) {
					swap(array, current, j);
					current--;
				}
			}
		}
	}

	public static void main(String[] args) {
		Integer[] yeet = { 7, 1, 8, 9, 2, 3, 6, 47, 2, 35 };
		print(yeet);
		mergeSort(yeet);
		print(yeet);
	}

	private static void print(Integer[] array) {
		System.out.println();
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length - 1)
				System.out.print(", ");
		}
	}

	// swaps indexes a and b in given array
	private static void swap(Integer[] array, int a, int b) {
		Integer temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
