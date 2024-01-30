import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counterResume;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        counterResume = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        counterResume++;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null && resume.toString().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i] != null && storage[i].toString().equals(uuid)) {
                storage[i] = null;
                for (int j = i; j < size(); j++) {
                    storage[j] = storage[j + 1];
                }
                counterResume--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return counterResume;
    }
}
