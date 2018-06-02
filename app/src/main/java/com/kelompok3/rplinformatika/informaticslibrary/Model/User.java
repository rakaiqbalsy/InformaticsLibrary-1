package com.kelompok3.rplinformatika.informaticslibrary.Model;

public class User {
    private String email;
    private String nama;
    private String password;

    public User() {
    }

    public User(String email, String nama, String password) {
        this.email = email;
        this.nama = nama;
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
