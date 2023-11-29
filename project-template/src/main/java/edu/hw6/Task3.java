package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Task3 {
    public static class FilterChain implements DirectoryStream.Filter<Path> {
        private List<DirectoryStream.Filter<Path>> filters;

        private FilterChain(List<DirectoryStream.Filter<Path>> filters) {
            this.filters = filters;
        }

        public static FilterChain regularFile() {
            return new FilterChain(Arrays.asList(new RegularFileFilter()));
        }

        public FilterChain and(DirectoryStream.Filter<Path> filter) {
            filters.add(filter);
            return this;
        }

        @Override
        public boolean accept(Path entry) throws IOException {
            for (DirectoryStream.Filter<Path> filter : filters) {
                if (!filter.accept(entry)) {
                    return false;
                }
            }
            return true;
        }

        // Реализации фильтров

        private static class RegularFileFilter implements DirectoryStream.Filter<Path> {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isRegularFile(entry);
            }
        }

        public static DirectoryStream.Filter<Path> readable() {
            return entry -> Files.isReadable(entry);
        }

        public static DirectoryStream.Filter<Path> largerThan(long size) {
            return entry -> Files.size(entry) > size;
        }

        public static DirectoryStream.Filter<Path> magicNumber(byte... magicBytes) {
            return entry -> {
                byte[] fileBytes = Files.readAllBytes(entry);
                return fileBytes.length >= magicBytes.length &&
                    Arrays.equals(Arrays.copyOf(fileBytes, magicBytes.length), magicBytes);
            };
        }

        public static DirectoryStream.Filter<Path> globMatches(String glob) {
            return entry -> entry.toString().matches(glob);
        }

        public static DirectoryStream.Filter<Path> regexContains(String regex) {
            return entry -> entry.getFileName().toString().matches(regex);
        }
    }

    public static void main(String[] args) {
        DirectoryStream.Filter<Path> filter = FilterChain.regularFile()
            .and(FilterChain.readable())
            .and(FilterChain.largerThan(100_000))
            .and(FilterChain.magicNumber((byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'))
            .and(FilterChain.globMatches("*.png"))
            .and(FilterChain.regexContains("[-]"));
    }
}
