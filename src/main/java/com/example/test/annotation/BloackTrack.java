package com.example.test.annotation;

public class BloackTrack {
    public static void test(Class<? extends Author> aClass) {
        Blog[] blogs = aClass.getAnnotationsByType(Blog.class);
        if (blogs != null && blogs.length > 0) {
            for (Blog blog : blogs) {
                System.out.println("名字" + blog.name());
                System.out.println("地址" + blog.address());
            }
        }
    }

    public static void main(String[] args) {
        Author author = new Author();
        test(author.getClass());
    }


    public void para(String... objects){

    }

    public void aa(String id, String bb){
        this.para(id,bb);
    }
}

