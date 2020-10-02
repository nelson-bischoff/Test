package com.example.TestV2.modele;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    //creation profil
    private Profil profil = new Profil(67,165,35,0);
    //resutat img
    private float img =(float)32.2;
    //message
    private String message ="Trop élevé";



    @Test
    public void getImg() {
        assertEquals(img,profil.getImg(),(float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message,profil.getMessage());
    }
}