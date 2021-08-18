package br.com.rchlo.store.domain;

public enum PaymentStatus {

    CREATED {
        @Override
        public PaymentStatus update() {
            return CONFIRMED;
        }

        @Override
        public PaymentStatus canceled() {
            return CANCELED;
        }
    },
    CONFIRMED {
        @Override
        public PaymentStatus update() {
            throw new IllegalArgumentException("A confirmed payment cannot be confirmed again!");
        }

        @Override
        public PaymentStatus canceled() {
            throw new IllegalArgumentException("A confirmed payment cannot be canceled!");
        }
    },
    CANCELED {
        @Override
        public PaymentStatus update() {
            throw new IllegalArgumentException("A canceled payment cannot be updated!");
        }

        @Override
        public PaymentStatus canceled() {
            throw new IllegalArgumentException("A canceled payment cannot be canceled again!");
        }
    };

    public abstract PaymentStatus update();

    public abstract PaymentStatus canceled();

}
