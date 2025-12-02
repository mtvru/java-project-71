package hexlet.code;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class DiffNode {
    public enum Status {
        ADDED, REMOVED, UPDATED, UNCHANGED
    }

    @JsonProperty
    private final String key;

    @JsonProperty
    private final Object oldValue;

    @JsonProperty
    private final Object newValue;

    @JsonProperty
    private final Status status;

    public DiffNode(
        final String pKey,
        final Object pOldValue,
        final Object pNewValue,
        final Status pStatus
    ) {
        this.key = pKey;
        this.oldValue = pOldValue;
        this.newValue = pNewValue;
        this.status = pStatus;
    }

    public String getKey() {
        return this.key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public boolean isStatusAdded() {
        return this.status == Status.ADDED;
    }

    public boolean isStatusRemoved() {
        return this.status == Status.REMOVED;
    }

    public boolean isStatusUnchanged() {
        return this.status == Status.UNCHANGED;
    }

    public boolean isStatusChanged() {
        return !this.isStatusUnchanged();
    }
}
