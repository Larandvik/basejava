import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counterResume;

    void clear() {
        for (int i = 0; i < counterResume; i++) {
            storage[i] = null;
        }
        counterResume = 0;
    }

    void save(Resume r) {
        storage[counterResume] = r;
        counterResume++;
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
            System.out.println("Такого резюме нет");
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < counterResume; i++) {
            try {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = null;
                    for (int j = i; j < counterResume; j++) {
                        storage[j] = storage[j + 1];
                    }
                    counterResume--;
                }
            } catch (NullPointerException e) {
                System.out.println("Такого резюме нет");
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, counterResume);
    }

    int size() {
        return counterResume;
    }
}
