package com.MRensen.transportApp.service;

import java.util.List;

public interface UserServiceInt<T>{
     List<T> getAll();
     T getOne(Long id);
     T addOne(T user);
     void deleteOne(Long id);
     T patchOne(Long id, T user);
     T updateOne(Long id, T user);

}
