package hexlet.code;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class DiffNode {
    /**
     * Enum representing the status of a node in the diff.
     */
    public enum Status {
        /**
         * Indicates that the node was added in the new version.
         */
        ADDED,
        /**
         * Indicates that the node was removed from the old version.
         */
        REMOVED,
        /**
         * Indicates that the node is unchanged between versions.
         */
        UNCHANGED
    }

    /** The key of the node. */
    @JsonProperty
    private final String key;

    /** The value associated with the key. */
    @JsonProperty
    private final Object value;

    /** The status indicating if the node was added, removed, or unchanged. */
    @JsonProperty
    private final Status status;

    /**
     * @param pKey the key of the node.
     * @param pValue the value associated with the key.
     * @param pStatus the status of the node.
     */
    public DiffNode(
        final String pKey,
        final Object pValue,
        final Status pStatus
    ) {
        this.key = pKey;
        this.value = pValue;
        this.status = pStatus;
    }

    /**
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the value.
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * @return the render value.
     */
    public String getRenderValue() {
        if (Utils.isObjectOrArray(this.value)) {
            return "[complex value]";
        }

        if (this.value instanceof String) {
            return "'" + this.value + "'";
        }

        return this.value == null ? "null" : this.value.toString();
    }

    /**
     * @return the status.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * @return true if the node is added, false otherwise.
     */
    public boolean isStatusAdded() {
        return this.status == Status.ADDED;
    }

    /**
     * @return true if the node is changed, false otherwise.
     */
    public boolean isStatusChanged() {
        return this.status != Status.UNCHANGED;
    }
}
