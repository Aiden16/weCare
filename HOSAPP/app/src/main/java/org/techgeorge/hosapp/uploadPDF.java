package org.techgeorge.hosapp;

public class uploadPDF {
    public String name;



    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public uploadPDF() {
    }

    public uploadPDF(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
