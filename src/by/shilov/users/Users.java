package by.shilov.users;

import java.util.*;

/**
 *
 *Нам нужен объект, который будет хранить информацию о Пользователе.
 *Id — нужно считать по порядку добавления нового пользователя.
 *Имя пользователя.
 *Его возраст.
 *Пол (male/female)
 *
 *Нужно предусмотреть хранение списка пользователей.
 *
 *Класс должен уметь.
 *Формировать список всех пользователей.
 *Формировать список пользователей по полу (MALE/FEMALE).
 *Возвращать количество пользователей в общем списке, и посчитать количество по признаку пола пользователя.
 *Посчитать общую сумму по возрасту пользователей, так же учесть по признаку пола.
 *Посчитать средний возраст, как общий так и по признаку пола.
 * https://javarush.ru/groups/posts/605-junit
 */
public class Users {

    private int id;
    private String name;
    private int age;
    private Sex sex;
    private static Map<Integer, Users> allUsers;
    private static int countId = 0;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users user = (Users) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                sex == user.sex;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, sex);
    }
    public Users(String name, int age, Sex sex) {
        if (allUsers == null){
            allUsers = new HashMap<>();
        }

        this.name = name;
        this.age = age;
        this.sex = sex;

        if (!hasUser()){
            countId++;
            this.id = countId;
            allUsers.put(id, this);
        }
    }
    private boolean hasUser(){
        for (Users user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
    public static List<Users> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    public static List<Users> getAllUsers(Sex sex){
        List<Users> listAllUsers = new ArrayList<>();
        for (Users user : allUsers.values()){
            if (user.sex == sex){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }
    public static int getHowManyUsers(){
        return allUsers.size();
    }

    public static int getHowManyUsers(Sex sex){
        return getAllUsers(sex).size();
    }
    public static int getAllAgeUsers(){
        int countAge = 0;
        for (Users user : allUsers.values()){
            countAge += user.age;
        }
        return countAge;
    }

    public static int getAllAgeUsers(Sex sex){
        int countAge = 0;
        for (Users user : getAllUsers(sex)){
            countAge += user.age;
        }
        return countAge;
    }
    public static int getAverageAgeOfAllUsers(){
        return getAllAgeUsers() / getHowManyUsers();
    }

    public static int getAverageAgeOfAllUsers(Sex sex){
        return getAllAgeUsers(sex) / getHowManyUsers(sex);
    }
    public static void main(String[] args) {
        new Users("Евгений", 35, Sex.MALE);
        new Users("Марина", 34, Sex.FEMALE);
        new Users("Алина", 7, Sex.FEMALE);


        System.out.println("Все пользователи:");
        Users.getAllUsers().forEach(System.out::println);
        System.out.println("Все пользователи: MALE");
        Users.getAllUsers(Sex.MALE).forEach(System.out::println);
        System.out.println("Все пользователи: FEMALE");
        Users.getAllUsers(Sex.FEMALE).forEach(System.out::println);
        System.out.println("================================================");
        System.out.println("       всех пользователей: " + Users.getHowManyUsers());
        System.out.println("  всех пользователей MALE: " + Users.getHowManyUsers(Sex.MALE));
        System.out.println("всех пользователей FEMALE: " + Users.getHowManyUsers(Sex.FEMALE));
        System.out.println("================================================");
        System.out.println("       общий возраст всех пользователей: " + Users.getAllAgeUsers());
        System.out.println("  общий возраст всех пользователей MALE: " + Users.getAllAgeUsers(Sex.MALE));
        System.out.println("общий возраст всех пользователей FEMALE: " + Users.getAllAgeUsers(Sex.FEMALE));
        System.out.println("================================================");
        System.out.println("       средний возраст всех пользователей: " + Users.getAverageAgeOfAllUsers());
        System.out.println("  средний возраст всех пользователей MALE: " + Users.getAverageAgeOfAllUsers(Sex.MALE));
        System.out.println("средний возраст всех пользователей FEMALE: " + Users.getAverageAgeOfAllUsers(Sex.FEMALE));
        System.out.println("================================================");
    }



}
