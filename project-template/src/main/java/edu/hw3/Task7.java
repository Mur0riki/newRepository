package edu.hw3;

import java.util.Comparator;

class NullKeyTreeMapDemo {
    static Comparator createNullHandlingComparator() {
        return Comparator.nullsFirst(Comparator.naturalOrder());
    }
}
