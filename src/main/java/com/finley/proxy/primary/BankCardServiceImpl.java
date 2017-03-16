package com.finley.proxy.primary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fengjiantao.
 * @since 3/16/17.
 */
public class BankCardServiceImpl implements BankCardService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void save(double amount) {
        logger.info("Execute save {} operation.", amount);
    }
}
