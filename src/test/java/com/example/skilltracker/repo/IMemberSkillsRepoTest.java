package com.example.skilltracker.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Sql("/sql/schema.sql")
public class IMemberSkillsRepoTest {

    @Autowired
    private IMemberSkillsRepo repo;

    @Test
    public void testJpaMethods(){

        assertEquals(1, repo.countBySkillId(1).intValue());
        assertEquals(1, repo.countBySkillLevel(1).intValue());

        // TODO more tests to be written
    }
}
