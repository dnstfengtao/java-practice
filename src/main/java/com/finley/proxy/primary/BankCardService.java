package com.finley.proxy.primary;

/**
 * @author fengjiantao.
 * @since 3/16/17.
 */
public interface BankCardService {

    void save(double amount);

    void withdraw(double amount);
}
