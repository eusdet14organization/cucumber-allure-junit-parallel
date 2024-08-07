package com.eurotech.context;

import com.github.javafaker.Faker;

import java.util.Random;

public class NewUserInfo {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public String gender;
    public String accommodation;
    public boolean volunteer;
    public String heardBy;
    public String extraGuest;

    public NewUserInfo(){
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = faker.internet().password(20, 30, true, true, true);
        phone = faker.phoneNumber().cellPhone();
        gender = faker.demographic().sex();
        accommodation = faker.lorem().characters(5,50);
        volunteer = new Random().nextBoolean();
        heardBy = "TV";
        extraGuest = faker.lorem().characters(5,50);
    }
}
