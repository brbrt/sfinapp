package hu.brbrt.overview;

import hu.brbrt.TestBase;
import hu.brbrt.account.Account;
import hu.brbrt.account.AccountController;
import hu.brbrt.tag.Tag;
import hu.brbrt.tag.TagController;
import hu.brbrt.transaction.Transaction;
import hu.brbrt.transaction.TransactionController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static hu.brbrt.core.TransactionType.Expense;
import static org.assertj.core.api.Assertions.assertThat;

public class OverviewTest extends TestBase {

    @Autowired
    private AccountController accountController;

    @Autowired
    private TagController tagController;

    @Autowired
    private TransactionController transactionController;

    @Autowired
    private OverviewController overviewController;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private int alice;
    private int food;

    @Before
    public void init() {
        alice = createAccount("Alice");
        food = createTag("food");
    }

    @Test
    public void crud() {
        int transactionId = transactionController.create(new Transaction()
                .setDescription("Gyros")
                .setAccountId(alice)
                .setTagId(food)
                .setType(Expense)
                .setAmount(-200.0)
                .setDate(LocalDate.now())
        );

        List<OverviewItem> overviewItems = overviewController.get();
        assertThat(overviewItems).hasSize(1);

        OverviewItem overviewItem = overviewItems.get(0);
        assertThat(overviewItem.getTransactionId()).isEqualTo(transactionId);
        assertThat(overviewItem.getDescription()).isEqualTo("Gyros");
        assertThat(overviewItem.getAccountName()).isEqualTo("Alice");
        assertThat(overviewItem.getTagName()).isEqualTo("food");
        assertThat(overviewItem.getAmount()).isEqualTo(-200.0);
        assertThat(overviewItem.getType()).isEqualTo(Expense);
        assertThat(overviewItem.getDate()).isEqualTo(LocalDate.now());
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
