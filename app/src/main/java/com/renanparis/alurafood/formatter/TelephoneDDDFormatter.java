package com.renanparis.alurafood.formatter;

public class TelephoneDDDFormatter {

    public String telephoneFormatter(String telephone) {
        return telephone.replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2-$3");
    }

    public String removePhoneFormat(String telephone) {

        String unformattedPhone = telephone.replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "");
        return unformattedPhone;
    }

}
