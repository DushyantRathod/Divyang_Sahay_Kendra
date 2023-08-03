package com.example.adip_scheme;

import android.graphics.pdf.PdfDocument;
import android.net.Uri;

public class UserHelperClas {
    private String id,name,pass1,pass2;


    public UserHelperClas(String e1, String e2, String e3, String e4) {
        id = e1;
        name = e2;
        pass1 =e3;
        pass2 =e4;
}

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id;}

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name= name;}

    public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }
}
