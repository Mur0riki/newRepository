package edu.hw6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Task1 {

    public class DiskMap<K, V> implements Map<K, V> {
        private File file;

        public DiskMap(String filePath) {
            this.file = new File(filePath);
        }

        @Override
        public int size() {
            try {
                return readMapFromFile().size();
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
        }

        @Override
        public boolean isEmpty() {
            try {
                return readMapFromFile().isEmpty();
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }

        @Override
        public boolean containsKey(Object key) {
            try {
                return readMapFromFile().containsKey(key);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean containsValue(Object value) {
            try {
                return readMapFromFile().containsValue(value);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public V get(Object key) {
            try {
                return readMapFromFile().get(key);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public V put(K key, V value) {
            try {
                Map<K, V> map = readMapFromFile();
                V previousValue = map.put(key, value);
                writeMapToFile(map);
                return previousValue;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public V remove(Object key) {
            try {
                Map<K, V> map = readMapFromFile();
                V removedValue = map.remove(key);
                writeMapToFile(map);
                return removedValue;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            try {
                Map<K, V> map = readMapFromFile();
                map.putAll(m);
                writeMapToFile(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void clear() {
            try {
                writeMapToFile(new HashMap<>());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Set<K> keySet() {
            try {
                return readMapFromFile().keySet();
            } catch (IOException e) {
                e.printStackTrace();
                return new HashSet<>();
            }
        }

        @Override
        public Collection<V> values() {
            try {
                return readMapFromFile().values();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            try {
                return readMapFromFile().entrySet();
            } catch (IOException e) {
                e.printStackTrace();
                return new HashSet<>();
            }
        }

        private Map<K, V> readMapFromFile() throws IOException {
            Map<K, V> map = new HashMap<>();

            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (!line.isEmpty()) {
                            String[] parts = line.split(":");
                            if (parts.length == 2) {
                                K key = (K) parts[0];
                                V value = (V) parts[1];
                                map.put(key, value);
                            }
                        }
                    }
                }
            }

            return map;
        }

        private void writeMapToFile(Map<K, V> map) throws IOException {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Entry<K, V> entry : map.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue());
                    writer.newLine();
                }
            }
        }
    }
}
