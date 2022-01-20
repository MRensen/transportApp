package com.MRensen.transportApp.service;

import java.util.List;

public interface UserServiceInt<T>{
    public List<T> getAll();
    public T getOne(Long id);
    public T addOne(T user);
    public void deleteOne(Long id);
    public T patchOne(Long id, T user);
    public T updateOne(Long id, T user);

}
