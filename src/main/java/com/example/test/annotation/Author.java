package com.example.test.annotation;

import lombok.Data;

@Blog(name = "ceshi")
@Data
public class Author {

    private String name;
    private int age;
    private Sex sex;
    private String email;

    public Author() {
    }

    public Author(String name, int age, Sex sex, String email) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    public enum Sex {
        MALE(1), FEMAIL(0);
        private int value;

        private Sex(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


}
