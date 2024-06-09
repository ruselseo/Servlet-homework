package dto;

import java.util.Objects;

public class GroupDto {
    private int groupId;
    private int groupNumber;

    public GroupDto(int groupId, int groupNumber) {
        this.groupId = groupId;
        this.groupNumber = groupNumber;
    }

    public GroupDto() {
    }

    public int getGroupId() {
        return groupId;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDto groupDto = (GroupDto) o;
        return getGroupId() == groupDto.getGroupId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGroupId());
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "groupId=" + groupId +
                ", groupNumber=" + groupNumber +
                '}';
    }
}
