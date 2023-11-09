package autocomplete;

import java.util.Comparator;
import java.util.List;


public class BinarySearchForAll {
	// flag indicating whether a key occurs at all in the list
	public static final int NOT_FOUND = -1;

	/**
	 * Returns the index of the first element in aList that equals key
	 *
	 * @param aList
	 *            Ordered (via specified comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Index of first item in aList matching key or -1 if not in aList
	 **/
	public static <Key> int firstIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		int searchSize = aList.size();
		int curIndex =  aList.size() / 2;
		Key curItem = aList.get(curIndex);
		if(comparator.compare(aList.get(0), key) == 0) {
			return 0;
		}
		while (true) {
			curItem = aList.get(curIndex);
			searchSize = (searchSize + 1) / 2;
			if (comparator.compare(key, curItem) < 0) {
				curIndex -= searchSize / 2;
			} else if (comparator.compare(key, curItem) > 0) {
				curIndex += searchSize / 2;
			} else {
				break;
			}

			if (searchSize < 1) {
				return NOT_FOUND;
			}
		}

		while(searchSize > 2 && curIndex - (searchSize / 2) - 1 >= 0 && comparator.compare(key, curItem) == 0) {
			curIndex -= (searchSize / 2) + 1;
			searchSize /= 2;
			curItem = aList.get(curIndex);
		}

		while(curIndex >= 0 && comparator.compare(key, curItem) == 0) {
			curItem = aList.get(--curIndex);
		}
		
		for (int i = curIndex; i <= (curIndex + searchSize) + 1; i++) {
			if (comparator.compare(key, aList.get(i)) == 0) {
				return i;
			}
		}
		
		return NOT_FOUND; 
	}


	/**
	 * Returns the index of the last element in aList that equals key
	 * 
	 * @param aList
	 *            Ordered (via specified comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Location of last item of aList matching key or -1 if no such key.
	 **/
	public static <Key> int lastIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		int searchSize = aList.size();
		int curIndex =  aList.size() / 2;
		Key curItem = aList.get(curIndex);
		if(comparator.compare(aList.get(aList.size() - 1), key) == 0) {
			return aList.size() - 1;
		}
		while (true) {
			curItem = aList.get(curIndex);
			searchSize = (searchSize + 1) / 2;
			if (comparator.compare(key, curItem) < 0) {
				curIndex -= searchSize / 2;
			} else if (comparator.compare(key, curItem) > 0) {
				curIndex += searchSize / 2;
			} else {
				break;
			}

			if (searchSize <= 1) {
				return NOT_FOUND;
			}
			
			
		}

		while(searchSize > 2 && curIndex + (searchSize / 2) < aList.size() && comparator.compare(key, curItem) == 0) {
			curIndex += (searchSize / 2) + 1;
			searchSize /= 2;
			curItem = aList.get(curIndex);
		}

		while(curIndex < aList.size() - 1 && comparator.compare(key, curItem) == 0) {
			curItem = aList.get(++curIndex);
		}

		for (int i = curIndex; i >= (curIndex - searchSize) - 1; i--) {
			if (comparator.compare(key, aList.get(i)) == 0) {
				return i;
			}
		}
		return NOT_FOUND;

	}

}
