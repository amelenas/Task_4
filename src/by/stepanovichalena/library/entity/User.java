package by.stepanovichalena.library.entity;

import java.io.Serializable;

public class User implements Serializable, Comparable {
    private static final long serialVersionUID = 354654523246754654L;
    private String name;
    private String password;
    private AccessLevel accessLevel;

    public User(){}

    public User(String name, String password,  AccessLevel accessLevel){
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        User user = (User) o;
        return getName().equals(user.getName()) && getPassword().equals(user.getPassword()) && getAccessLevel() == user.getAccessLevel();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + password.hashCode();
        result = prime * result + accessLevel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(
                this.getClass()+"{"+"name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                '}').toString();
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return name.compareTo(user.name);
    }
}
