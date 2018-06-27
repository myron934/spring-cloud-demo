package com.netease.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.netease.example.demo.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
