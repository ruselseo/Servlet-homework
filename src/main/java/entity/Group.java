package entity;

import java.util.Objects;

public class Group {
    private Integer id;
    private Integer number;

    public Group(Integer id, Integer number) {
        this.id = id;
        this.number = number;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setName(int number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group course = (Group) o;
        return getId() == course.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
