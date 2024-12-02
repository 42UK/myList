package list;

import java.util.*;

/**
 * Реализация собственного ArrayList.
 * Поддерживает основные операции: добавление, удаление, доступ к элементам, очистка, сортировка.
 *
 * @param <T> тип элементов, хранимых в списке
 */
public class MyList<T> {

    /**
     * Сообщение об ошибке, которое используется при возникновении исключения
     * IndexOutOfBoundsException. Это сообщение указывает на то, что был
     * выполнен доступ к элементу по индексу, который выходит за пределы
     * допустимого диапазона.
     */
    private static final String MESSAGE_EXCEPTION = "Index out of bounds";

    // Массив для хранения элементов
    private Object[] elements;

    // Размер массива по умолчанию
    private static final int DEFAULT_CAPACITY = 10;

    // Текущий размер списка
    private int size;

    // Конструктор по умолчанию
    public MyList() {
        elements = new Object[DEFAULT_CAPACITY];  // Начальный размер массива
        size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        checkCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу.
     *
     * @param index   индекс, куда нужно вставить элемент
     * @param element элемент, который нужно добавить
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(MESSAGE_EXCEPTION);
        }
        checkCapacity();
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент, хранящийся в списке
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(MESSAGE_EXCEPTION);
        }
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index индекс элемента, который нужно удалить
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(MESSAGE_EXCEPTION);
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; // освобождение памяти
        size--;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Заменяет элемент по индексу.
     *
     * @param index   индекс элемента
     * @param element новый элемент
     * @return старый элемент
     */
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(MESSAGE_EXCEPTION);
        }
        T oldElement = (T) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * Сортирует список с использованием QuickSort.
     *
     * @param comparator компаратор для сортировки
     */
    public void quickSort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) elements[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) elements[j], pivot) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Проверяет, достаточно ли места для добавления нового элемента.
     * Если места нет, увеличивает размер массива.
     */
    private void checkCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }

    /**
     * Возвращает текущий размер списка.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строка, представляющая все элементы списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}