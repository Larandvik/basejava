package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    /*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }
*/

    private static final Comparator<Resume> RESUME_COMPARATOR = new Comparator<Resume>() {
        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    };

    @Override
    protected void saveResumeArray(Resume resume, Object searchKey) {
        int index = (int) searchKey;
        if (index < 0) {
            index = (-index) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
        }
        storage[index] = resume;
    }

    @Override
    protected void deleteResumeArray(Object searchKey) {
        int index = (int) searchKey;
        if (index == STORAGE_LIMIT - 1) {
            storage[index] = null;
            return;
        }
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        if (size == 0) {
            return false;
        }
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            String current = storage[middle].getUuid();
            if (current != null) {
                int compareResult = current.compareTo((String) searchKey);
                if (compareResult == 0) {
                    return true;
                } else if (compareResult < 0) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            } else {
                left = middle + 1;
            }
        }
        return false;
    }
}
