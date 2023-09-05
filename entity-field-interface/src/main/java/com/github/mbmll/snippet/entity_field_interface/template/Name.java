package com.github.mbmll.snippet.entity_field_interface.template;

/**
 *
 * @param <T>
 */

public interface Name<T> extends EntityField {
    default Name<T> assign(Name<T> name) {
        setName(name.getName());
        return this;
    }

    /**
     * @return
     */
    T getName();

    /**
     * @param name
     */
    void setName(T name);
}
