package lesson5;

public class Cat {
    public String name;
    public String color;
    public int age;

    public Cat(String name, String color, int age) {
        this.name = name.toUpperCase();
        this.color = color;
        this.age = age;
    }

    public Cat(String name, String color) {
        this(name, color,0);
    }

    public Cat(String name) {
        this(name,null);
    }

    public Cat(){
        this(null);

    }

    @Override
    public String toString(){
        return "Cat{" + "name="+name+ '\''+", color=" + color +'\''+", age="+age + '}';
    }
}
