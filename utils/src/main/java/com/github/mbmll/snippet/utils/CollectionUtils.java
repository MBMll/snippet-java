package com.github.mbmll.snippet.utils;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    public static <T> List<T> toTree(List<T> sources, Collector<T> collector) {
        return getChildren(sources, null, collector);
    }

    private static <T> List<T> getChildren(List<T> sources, T parent, Collector<T> collector) {
        if (!isEmpty(sources)) {
            Map<Boolean, List<T>> collect = sources.stream().collect(Collectors.groupingBy(child -> collector.compare(parent, child)));
            List<T> childs = collect.get(true);
            if (!isEmpty(childs)) {
                for (T child : childs) {
                    collector.putAll(child, getChildren(collect.get(false), child, collector));
                }
                return childs;
            }
        }
        return Collections.emptyList();
    }

    public interface Collector<T> {
        boolean compare( T parent, T child);

        void putAll(T parent, Collection<T> collection);
    }
}
