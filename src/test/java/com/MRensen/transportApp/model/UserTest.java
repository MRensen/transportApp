package com.MRensen.transportApp.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @BeforeEach
    void setup(){
         user = new User();
    }

    @Test
    void setRoleSetsAuthority(){
        user.setUsername("test");
        user.setRole("planner");
        Authority expected = new Authority("test", "planner");
        Authority actual = user.getAuthorities().iterator().next();
        assertEquals(expected.getAuthority(), actual.getAuthority());
        assertEquals(expected.getUsername(), actual.getUsername());
    }
}
