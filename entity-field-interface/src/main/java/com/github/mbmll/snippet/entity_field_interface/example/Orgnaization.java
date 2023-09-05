package com.github.mbmll.snippet.entity_field_interface.example;

import com.github.mbmll.snippet.entity_field_interface.template.Id;
import com.github.mbmll.snippet.entity_field_interface.template.Name;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author xlc
 * @Description
 * @Date 2023/2/10 上午 09:58
 */
@Data
@AllArgsConstructor
public class Orgnaization implements Id<Long>, Name<String> {
    private Long id;
    private String name;
}
