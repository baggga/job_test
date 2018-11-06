package com.baggga.job_test;

import com.baggga.job_test.bean.VisitInfo;
import com.baggga.job_test.repository.VisitInfoRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(initializers = {RepositoryTest.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer =
            (PostgreSQLContainer) new PostgreSQLContainer("postgres:10.4")
                    .withDatabaseName("mydb")
                    .withUsername("postgres")
                    .withPassword("password")
                    .withStartupTimeout(Duration.ofSeconds(600));

    @Autowired
    private VisitInfoRepository visitInfoRepository;

    @Test
    public void testUniqueUsersCount() {
        LocalDate today = LocalDate.now();
        Assert.assertThat(this.visitInfoRepository.count(), Matchers.equalTo(0L));
        Assert.assertThat(this.visitInfoRepository.uniqueUsersCount(today, today), Matchers.equalTo(0));
        this.visitInfoRepository.save(new VisitInfo("testUserId_1", "testPageId_1", today));
        this.visitInfoRepository.save(new VisitInfo("testUserId_2", "testPageId_1", today));
        this.visitInfoRepository.save(new VisitInfo("testUserId_2", "testPageId_2", today));
        Assert.assertThat(this.visitInfoRepository.count(), Matchers.equalTo(3L));
        Assert.assertThat(this.visitInfoRepository.uniqueUsersCount(today, today), Matchers.equalTo(2));
    }

    @Test
    public void testRegularUsersCount() {
        LocalDate localDateFrom = LocalDate.of(2018, 11, 1);
        LocalDate localDateTo = LocalDate.of(2018, 12, 1);
        Assert.assertThat(this.visitInfoRepository.count(), Matchers.equalTo(0L));
        for (int i = 0; i < 9; i++) {
            this.visitInfoRepository.save(new VisitInfo("testUserId_1", "testPageId_" + i, LocalDate.of(2018, 11, 7)));
            this.visitInfoRepository.save(new VisitInfo("testUserId_2", "testPageId_" + i, LocalDate.of(2018, 11, 7)));
        }
        Assert.assertThat(this.visitInfoRepository.regularUsersCount(localDateFrom, localDateTo), Matchers.equalTo(0));
        this.visitInfoRepository.save(new VisitInfo("testUserId_1", "testPageId_9", LocalDate.of(2018, 11, 7)));
        Assert.assertThat(this.visitInfoRepository.regularUsersCount(localDateFrom, localDateTo), Matchers.equalTo(1));
        this.visitInfoRepository.save(new VisitInfo("testUserId_2", "testPageId_9", LocalDate.of(2018, 12, 7)));
        Assert.assertThat(this.visitInfoRepository.regularUsersCount(localDateFrom, localDateTo), Matchers.equalTo(1));
        this.visitInfoRepository.save(new VisitInfo("testUserId_2", "testPageId_9", LocalDate.of(2018, 11, 7)));
        Assert.assertThat(this.visitInfoRepository.regularUsersCount(localDateFrom, localDateTo), Matchers.equalTo(2));
    }


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
