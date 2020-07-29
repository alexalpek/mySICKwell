package com.alex.mysickwell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.Objects;

@Builder
@ToString
@Getter
public class Column {

    @NonNull
    private final String name;
    @NonNull
    private final ColumnType type;
    @Builder.Default
    private final boolean notNull = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return name.equals(column.name) &&
                type == column.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
