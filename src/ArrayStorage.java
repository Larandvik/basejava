/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        Resume result;
        try {
            for (Resume resume : storage) {
                if (resume.toString().equals(uuid)) {
                    result = resume;
                    return result;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Такого резюме нет или база пуста");
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
