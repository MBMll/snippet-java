package com.github.mbmll.snippet.entity_field_interface.template;

/**
 *
 */

public interface Id<T> extends EntityField {
    /**
     * @param id
     *
     * @return
     */
    default Id<T> assign(Id<T> id) {
        setId(id.getId());
        return this;
    }

    T getId();

    void setId(T id);
}
