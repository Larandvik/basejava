/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        Resume result;
        for (Resume resume : storage) {
            if (resume.toString().equals(uuid)) {
                result = resume;
                return result;
            }
        }
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            size++;
        }
        return size;
    }
}
