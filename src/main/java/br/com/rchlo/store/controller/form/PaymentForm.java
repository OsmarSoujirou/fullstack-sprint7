package br.com.rchlo.store.controller.form;

import br.com.rchlo.store.domain.Card;
import br.com.rchlo.store.domain.PaymentStatus;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class PaymentForm {


    @DecimalMin(value = "0.0", inclusive = false, message = "Required  value greater than zero!")
    private BigDecimal value;

    @NotEmpty(message = "Required value! non-empty")
    @Length(max = 100, message = "Maximum number of characters 100!")
    private String cardClientName;

    @Pattern(regexp = "^[0-9]{16}$", message = "Required value, 16 digits!")
    private String cardNumber;

    @Pattern(regexp = "((0[123456789]|10|11|12)[/|-]20(2[2-9]))|(20(2[2-9])[/|-](0[123456789]|10|11|12))",
            message = "Required value greater than 2021!")
    private String cardExpiration;

    @Pattern(regexp = "^[0-9]{3}$", message = "Required value, 3 digits!")
    private String cardVerificationCode;

    private Card card;

    private PaymentStatus status = PaymentStatus.CREATED;

    public PaymentForm(BigDecimal value, String cardClientName, String cardNumber, String cardExpiration, String cardVerificationCode) {
        this.value = value;
        this.cardClientName = cardClientName;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardVerificationCode = cardVerificationCode;
        this.card = new Card(cardClientName, cardNumber, cardExpiration, cardVerificationCode);
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCardClientName() {
        return cardClientName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpiration() {
        return cardExpiration;
    }

    public String getCardVerificationCode() {
        return cardVerificationCode;
    }

    public Card getCard() {
        return card;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
