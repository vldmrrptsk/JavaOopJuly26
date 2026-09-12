package ru.academits.repetskiy.hash_table;

import java.util.Iterator;

public interface MyCollection<E> {
    int size();

    boolean isEmpty();

    boolean add(E e);

    boolean contains(Object o);

    boolean remove(Object o);

    boolean clear();

    //Iterator<E> iterator();
}
