package com.domebarbershop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private final String FILE = "users.dat";

    public UserManager(){
        users = new ArrayList<>();
        loadUsers();
        if(users.isEmpty()) initializeDefaults();
    }

    public void initializeDefaults(){
        users.add(new User("Kelvin","Kelvin123","Barber"));
        users.add(new User("Mike","Mike123","Barber"));
        users.add(new User("Kester","Kester123","Barber"));
        users.add(new User("John","John123","Barber"));
        users.add(new User("Cash","Cash123","Barber"));
        users.add(new User("Admin1","admin123","Admin"));
        users.add(new User("Admin2","admin123","Admin"));
        saveUsers();
    }

    public boolean register(User u){
        for(User user : users){
            if(user.getUsername().equalsIgnoreCase(u.getUsername())) return false;
        }
        users.add(u);
        saveUsers();
        return true;
    }

    public User login(String username, String password, String role){
        for(User u : users){
            if(u.getUsername().equals(username) &&
               u.getPassword().equals(password) &&
               u.getRole().equals(role)) return u;
        }
        return null;
    }

    public List<User> getUsers(){ return users; }

    public void saveUsers(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))){
            oos.writeObject(users);
        } catch(Exception e){ e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public void loadUsers(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))){
            users = (List<User>) ois.readObject();
        } catch(Exception e){
            users = new ArrayList<>();
        }
    }

    public List<User> getBarbers(){
        List<User> barbers = new ArrayList<>();
        for(User u : users){
            if(u.getRole().equals("Barber")) barbers.add(u);
        }
        return barbers;
    }
}
