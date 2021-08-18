package br.com.rchlo.store.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Card {

    private String clientName;
    private String number;
    private String expiration;
    private String verificationCode;

    /** @deprecated */
    protected Card() {
    }

    public Card(String clientName, String number, String expiration, String verificationCode) {
        this.clientName = clientName;
        this.number = number;
        this.expiration = expiration;
        this.verificationCode = verificationCode;
    }

    public String getClientName() {
        return clientName;
    }

    public String getNumber() {
        return number;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
