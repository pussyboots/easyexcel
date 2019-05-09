package com.qingwu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qingwu.pojo.UserLike;

public interface UserRepository extends JpaRepository<UserLike, Long> {

}
