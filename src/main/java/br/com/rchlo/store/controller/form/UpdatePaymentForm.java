package br.com.rchlo.store.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdatePaymentForm {
    @NotNull
    @NotEmpty
    private Long id;
    @NotNull
    @NotEmpty
    private String status;
}
