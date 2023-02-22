package com.github.mbmll.snippet.utils;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author xlc
 * @Description
 * @Date 2023/2/22 22:52
 */

public class CollectionUtils {
    public static boolean isEmpty(Collection target) {
        return target == null || target.isEmpty();
    }

    public static <T> List<T> toTree(List<T> sources, Comparator<T> comparator, Collector<T> collector) {
        return getChildren(sources, null, comparator, collector);
    }

    private static <T> List<T> getChildren(List<T> sources, T parent, Comparator<T> comparator, Collector<T> collector) {
        if (!isEmpty(sources)) {
            Map<Boolean, List<T>> collect = sources.stream().collect(Collectors.groupingBy(child -> comparator.compare(parent, child)));
            List<T> childs = collect.get(true);
            if (!isEmpty(childs)) {
                for (T child : childs) {
                    collector.putAll(child, getChildren(collect.get(false), child, comparator, collector));
                }
                return childs;
            }
        }
        return Collections.emptyList();
    }

    /**
     * @param sources 
     * @param classifier
     * @param <T>
     * @param <K>
     *
     * @return
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> sources, Function<T, K> classifier) {
        HashMap<K, List<T>> map = new HashMap<>();
        for (T source : sources) {
            K key = classifier.apply(source);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
                map.get(key).add(source);
            }
        }
        return map;
    }

    public static <T, K> List<T> toTree(List<T> sources, Function<T, K> getParent, Function<T, K> getCurrent, Collector<T> collector) {
        if (isEmpty(sources)) {
            return sources;
        }
        Map<K, List<T>> groups = groupBy(sources, getParent);
        List<T> ts = groups.get(null);
        for (T t : ts) {
            collector.putAll(t, groups.get(getCurrent.apply(t)));
        }
        return ts;
    }

    public interface Comparator<T> {
        boolean compare(T parent, T child);
    }

    public interface Collector<T> {
//        default boolean compare(T parent, T child) {
//            return false;
//        }

        void putAll(T parent, Collection<T> collection);

    }
}
