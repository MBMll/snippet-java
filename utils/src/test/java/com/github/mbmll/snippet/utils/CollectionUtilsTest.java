package com.github.mbmll.snippet.utils;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author xlc
 * @Description
 * @Date 2023/2/23 1:25
 */
public class CollectionUtilsTest {
    @Test
    public void isEmpty() {
    }

    @Test
    public void toTree() {
        ArrayList<Menu> list = new ArrayList<>();
        MockConfig config = MockConfig.newInstance().excludes("subMenus");
        for (int i = 0; i < 10; i++) {
            Menu parent = JMockData.mock(Menu.class, config);
            parent.parentId = null;
            list.add(parent);
            for (int j = 0; j < 10; j++) {
                Menu child = JMockData.mock(Menu.class, config);
                child.parentId = parent.id;
                list.add(child);
            }
        }
        List<Menu> menus = CollectionUtils.toTree(list,
                (parent, child) -> {
                    if (parent == null) {
                        return null == child.parentId;
                    }
                    return Objects.equals(parent.id, child.parentId);
                },
                (parent, collection) -> {
                    parent.subMenus = new ArrayList<>(collection);
                });
        System.out.println(menus);
    }

    public static class Menu {
        private Long id;
        private Long parentId;
        private List<Menu> subMenus;

        @Override
        public String toString() {
            return "{" +
                    "id:" + id +
                    ", parentId:" + parentId +
                    ", subMenus:" + subMenus +
                    '}';
        }
    }
}