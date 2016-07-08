package com.example.intern.intern;

public class ContactData {
    String value;
    boolean isHeader;

    public ContactData(String value, boolean isHeader) {
        this.value = value;
        this.isHeader = isHeader;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }
}
