package com.github.mbmll.snippet.utils;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @Author xlc
 * @Description
 * @Date 2023/6/3 1:43
 */

public class SorterTest {

    @Test
    public void t1() {

        List<Entity> list = new ArrayList<>();
        MockConfig config = MockConfig.newInstance();
        for (int i = 0; i < 10; i++) {
            Entity entity = JMockData.mock(Entity.class, config);
            list.add(entity);
        }
        Sorter<Entity, String> sorter = new Sorter<Entity, String>(0, list) {
            @Override
            public String getKey(Entity entity) {
                return entity.id;
            }

            @Override
            public Integer getValue(Entity entity) {
                return entity.serialNum;
            }

            @Override
            public void setValue(Entity entity, int v) {
                entity.serialNum = v;
            }
        };
        list.get(2).serialNum = null;
        System.out.println(list);
        System.out.println(sorter.move(list.get(0), 4));
    }

    public static class Entity {

        private String id;
        private Integer serialNum;

        @Override
        public String toString() {
            return "Entity{" +
                "id='" + id + '\'' +
                ", serialNum=" + serialNum +
                '}';
        }
    }
}
