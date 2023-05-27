package com.github.mbmll.snippet.utils;

/**
 * @Author xlc
 * @Description
 * @Date 2023/5/28 0:09
 */

public class HashUtil {

    /**
     * 扰动函数；在 JDK 的 HashMap 中，对于一个元素的存放，需要进行哈希散列。而为了让散列更加均匀，所以添加了扰动函数。 因此在这里借鉴 HashMap 源码
     *
     * @param key        key
     * @param bucketSize size of bucket
     * @return hash index
     * @see java.util.HashMap#hash(Object)
     */
    public static int hash4Slice(Object key, int bucketSize) {
        return (bucketSize - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));
    }

}
