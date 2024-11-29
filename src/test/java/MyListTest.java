import list.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса MyList.
 * Этот класс содержит тесты для методов добавления, удаления, получения,
 * установки значений, очистки и сортировки элементов в списке.
 */
class MyListTest {

    private MyList<Integer> list;

    /**
     * Инициализация нового списка перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        list = new MyList<>(); // Инициализация нового списка перед каждым тестом
    }

    /**
     * Тестирует добавление элементов и их получение.
     */
    @Test
    void testAddAndGet() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
        assertEquals(3, list.size());
    }

    /**
     * Тестирует добавление элемента по указанному индексу.
     */
    @Test
    void testAddAtIndex() {
        list.add(10);
        list.add(20);
        list.add(1, 15); // Вставка 15 по индексу 1

        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(20, list.get(2));
        assertEquals(3, list.size());
    }

    /**
     * Тестирует удаление элемента по индексу.
     */
    @Test
    void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(1); // Удаление элемента по индексу 1 (20)

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(2, list.size());
    }

    /**
     * Тестирует очистку списка.
     */
    @Test
    void testClear() {
        list.add(10);
        list.add(20);

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    /**
     * Тестирует установку значения по индексу и проверяет возвращаемое старое значение.
     */
    @Test
    void testSet() {
        list.add(10);
        list.add(20);

        int oldValue = list.set(1, 25); // Установка значения 25 по индексу 1

        assertEquals(20, oldValue); // Проверка старого значения
        assertEquals(25, list.get(1)); // Проверка нового значения
    }

    /**
     * Тестирует сортировку списка с использованием QuickSort.
     */
    @Test
    void testQuickSort() {
        list.add(30);
        list.add(10);
        list.add(20);

        // Сортировка списка
        list.quickSort(Integer::compareTo);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    /**
     * Тестирует выброс исключения IndexOutOfBoundsException при попытке получить элемент по недопустимому индексу.
     */
    @Test
    void testIndexOutOfBoundsExceptionOnGet() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(-1); // Попытка получить элемент по отрицательному индексу
        });

        assertEquals("Index out of bounds", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0); // Попытка получить элемент из пустого списка
        });

        assertEquals("Index out of bounds", exception.getMessage());
    }

    /**
     * Тестирует выброс исключения IndexOutOfBoundsException при попытке удалить элемент по недопустимому индексу.
     */
    @Test
    void testIndexOutOfBoundsExceptionOnRemove() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(-1); // Попытка удалить элемент по отрицательному индексу
        });

        assertEquals("Index out of bounds", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.remove(0); // Попытка удалить элемент из пустого списка
        });

        assertEquals("Index out of bounds", exception.getMessage());
    }

    /**
     * Тестирует выброс исключения IndexOutOfBoundsException при попытке установить элемент по недопустимому индексу.
     */
    @Test
    void testIndexOutOfBoundsExceptionOnSet() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(-1, 100); // Попытка установить элемент по отрицательному индексу
        });

        assertEquals("Index out of bounds", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.set(0, 100); // Попытка установить элемент в пустом списке
        });

        assertEquals("Index out of bounds", exception.getMessage());
    }

    /**
     * Тестирует очистку списка после добавления элементов.
     */
    @Test
    void testToEmpty() {
        list.add(5);
        list.add(10);
        list.clear();

        // Проверяем, что список пустой после очистки
        assertTrue(list.isEmpty());
    }

    /**
     * Тестирует корректность метода toString().
     */
    @Test
    void testToString() {
        list.add(5);
        list.add(10);

        // Проверяем строковое представление списка
        assertEquals("[5, 10]", list.toString());
    }
}