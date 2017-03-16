package com.finley.proxy.primary;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 3/16/17.
 */
public class BankCardServiceTest {

    @Test
    public void testGetProxy() {
        final BankCardProxyFactory bankCardProxyFactory = new BankCardProxyFactory();
        BankCardService originalBankCardService = new BankCardServiceImpl();
        BankCardService proxyBankCardService = bankCardProxyFactory.getBankCardService(originalBankCardService);
        proxyBankCardService.save(1000);
        proxyBankCardService.withdraw(1000);
    }

}
