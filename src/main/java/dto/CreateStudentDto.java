package dto;

import java.util.Objects;

public class CreateStudentDto {
    private String name;
    private String email;
    private String gender;
    private Integer group;
    private Integer classes;

    public CreateStudentDto(String name, String email, String gender, Integer group, Integer classes) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.group = group;
        this.classes = classes;
    }

    public CreateStudentDto() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Integer getGroup() {
        return group;
    }

    public Integer getClasses() {
        return classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateStudentDto that = (CreateStudentDto) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getGender(), that.getGender()) && Objects.equals(getGroup(), that.getGroup()) && Objects.equals(getClasses(), that.getClasses());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getGender(), getGroup(), getClasses());
    }

    @Override
    public String toString() {
        return "CreateStudentDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", group=" + group +
                ", classes=" + classes +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }
}
