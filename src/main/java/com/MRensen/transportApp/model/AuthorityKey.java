package com.MRensen.transportApp.model;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityKey implements Serializable {
    private Long id;
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorityKey that = (AuthorityKey) o;
        return id.equals(that.id) &&
                authority.equals(that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }

}