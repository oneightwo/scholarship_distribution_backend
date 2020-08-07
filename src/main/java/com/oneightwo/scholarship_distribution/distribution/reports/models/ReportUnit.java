package com.oneightwo.scholarship_distribution.distribution.reports.models;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReportUnit<ID extends Comparable<ID>, V> implements Comparable<ReportUnit<ID, V>> {

    private ID id;

    private V value;

    @Override
    public int compareTo(@NotNull ReportUnit<ID, V> o) {
        return this.id.compareTo(o.getId());
    }
}
