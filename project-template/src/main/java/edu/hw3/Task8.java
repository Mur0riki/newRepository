package edu.hw3;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class BackwardIterator<T> implements Iterator {
    private final ListIterator listIterator;

    public BackwardIterator(Collection<T> collection) {
        List list = List.copyOf(collection);
        this.listIterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasPrevious();
    }

    @Override
    public T next() {
        return (T) listIterator.previous();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }

}
