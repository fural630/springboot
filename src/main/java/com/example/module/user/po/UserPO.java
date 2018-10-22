package com.example.module.user.po;

public class UserPO {
    private String id;

    private String name;

    private String birthDay;

    private String passWord;

    private Integer age;

    private Integer sex;

    private String email;

    private String phone;

    private String idCard;

    private String avatarId;

    private String departmentCid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getDepartmentCid() {
        return departmentCid;
    }

    public void setDepartmentCid(String departmentCid) {
        this.departmentCid = departmentCid;
    }

	@Override
	public String toString() {
		return "UserPO [id=" + id + ", name=" + name + ", birthDay=" + birthDay + ", passWord=" + passWord + ", age="
				+ age + ", sex=" + sex + ", email=" + email + ", phone=" + phone + ", idCard=" + idCard + ", avatarId="
				+ avatarId + ", departmentCid=" + departmentCid + "]";
	}
}