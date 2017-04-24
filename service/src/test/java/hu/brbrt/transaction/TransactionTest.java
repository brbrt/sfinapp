package hu.brbrt.transaction;

import hu.brbrt.TestBase;
import hu.brbrt.account.Account;
import hu.brbrt.account.AccountController;
import hu.brbrt.tag.Tag;
import hu.brbrt.tag.TagController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;

import static hu.brbrt.core.TransactionType.Expense;
import static hu.brbrt.core.TransactionType.Income;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest extends TestBase {

    @Autowired
    private AccountController accountController;

    @Autowired
    private TagController tagController;

    @Autowired
    private TransactionController transactionController;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private int alice;
    private int bob;
    private int food;
    private int work;

    @Before
    public void init() {
        alice = createAccount("Alice");
        bob = createAccount("Bob");
        food = createTag("food");
        work = createTag("work");
    }

    @Test
    public void crud() {
        int id = transactionController.create(new Transaction()
                .setDescription("Gyros")
                .setAccountId(alice)
                .setTagId(food)
                .setType(Expense)
                .setAmount(-200.0)
                .setDate(LocalDate.now())
        );

        Transaction transaction = transactionController.get(id);
        assertThat(transaction.getId()).isEqualTo(id);
        assertThat(transaction.getDescription()).isEqualTo("Gyros");
        assertThat(transaction.getAccountId()).isEqualTo(alice);
        assertThat(transaction.getTagId()).isEqualTo(food);
        assertThat(transaction.getAmount()).isEqualTo(-200.0);
        assertThat(transaction.getType()).isEqualTo(Expense);
        assertThat(transaction.getDate()).isEqualTo(LocalDate.now());

        transactionController.update(transaction
                .setDescription("Work")
                .setAccountId(bob)
                .setTagId(work)
                .setAmount(2000.0)
                .setType(Income)
                .setDate(LocalDate.now().minusDays(1))
        );

        transaction = transactionController.get(id);
        assertThat(transaction.getId()).isEqualTo(id);
        assertThat(transaction.getDescription()).isEqualTo("Work");
        assertThat(transaction.getAccountId()).isEqualTo(bob);
        assertThat(transaction.getTagId()).isEqualTo(work);
        assertThat(transaction.getAmount()).isEqualTo(2000.0);
        assertThat(transaction.getType()).isEqualTo(Income);
        assertThat(transaction.getDate()).isEqualTo(LocalDate.now().minusDays(1));

        transactionController.delete(id);

        expectedException.expect(EmptyResultDataAccessException.class);
        transactionController.get(id);
    }

    private int createAccount(String name) {
        Account account = new Account()
                .setName(name)
                .setTechnical(false);
        return accountController.create(account);
    }

    private int createTag(String name) {
        Tag tag = new Tag()
                .setName(name);
        return tagController.create(tag);
    }

}
