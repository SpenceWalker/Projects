package com.techelevator;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
public class JdbcAccountTest extends BaseDaoTest {

    private static final Account ACCOUNT_1 = new Account(1, 1, 1000);
    private static final Account ACCOUNT_2 = new Account(2,2,1000);
    private static final Account ACCOUNT_3 = new Account(3, 3,1000);

    private JdbcAccountDao sut;




}
