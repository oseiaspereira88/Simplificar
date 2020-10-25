package com.example.vemcar.models;

import android.content.Context;

import com.facebook.AccessToken;
import com.fasterxml.jackson.annotation.JsonIgnore;
import android.content.SharedPreferences;
import com.example.vemcar.util.CryptWithMD5;
import com.example.vemcar.util.LibraryClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.firebase.database.DatabaseReference;
import java.security.NoSuchAlgorithmException;

@JsonIgnoreProperties({"id", "senha"})
public class User {
    private static final String TOKEN = "com.example.vemcar.models.User.TOKEN";
    private static final String ID = "com.example.vemcar.models.User.ID";
    private static final String NOME = "com.example.vemcar.models.User.NOME";
    private static final String EMAIL = "com.example.vemcar.models.User.EMAIL";
    private String id, nome, email, senha;
    public static String PREF = "com.example.vemcar.PREF";

    public User(){}

    public void saveInFirebase(){
        String id = getId();
        DatabaseReference bdRef = LibraryClass.getFirebaseDB().getReference();
        bdRef = bdRef.child("users").child(id);
        this.nome=null;
        bdRef.setValue(this);
    }

    public static void saveTokenSP(Context ctx, AccessToken accessToken){
        LibraryClass.saveSP(ctx, TOKEN, accessToken.getToken());
    }

    public static String getStringTokenSP(Context ctx){
        String token = LibraryClass.getSP(ctx, TOKEN);
        return token;
    }

    public void saveNameAndEmailSP(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(NOME, nome).apply();
        sp.edit().putString(EMAIL, email).apply();
    }

    public void restaureNameSP(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String nome = sp.getString(NOME,"");
        this.nome = nome;
    }

    public void deleteNameSP(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(NOME, "").apply();
    }

    public static boolean haveNameAndIsEqualEmailSP(Context ctx, String email){
        boolean is = false;
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String nome = sp.getString(NOME,"");
        String emailSP = sp.getString(EMAIL, "");

        if ((nome!=null && nome !="") && email.equals(emailSP)){
            is=true;
        }
        return is;
    }

    public void gerarCryptSenha() throws NoSuchAlgorithmException {
        senha = CryptWithMD5.gerarMD5Hast(senha);
    }

    public void saveIdSP(Context ctx, String id){
        SharedPreferences sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(ID, id).apply();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
