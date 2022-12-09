package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.EmployeeDto;
import com.datauser.goalmanagement.model.goals.Employer;
import com.datauser.goalmanagement.model.keycloak.User;
import com.datauser.goalmanagement.repository.keycloack.UserRepository;
import com.datauser.goalmanagement.services.UserService;
import com.datauser.goalmanagement.services.ValidationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Test
    void getAllEmployee() {
        UserController userController = new UserController(new UserService(new UserRepository() {
            @Override
            public Optional<User> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<User> findAll() {
                return List.of(
                        new User("20L", "Ani", "Mkrtumyan", "ani@gmail.com"),
                        new User("21L", "Annabelle", "Petrosian", "annabelle@gmail.com"),
                        new User("22L", "Annie", "Hakian", "annie@gmail.com"));
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(String s) {
                return null;
            }

            @Override
            public User getById(String s) {
                return null;
            }

            @Override
            public User getReferenceById(String s) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }
        }), new ValidationService());
        var response = userController.getAllEmployee();
        var body = (List<EmployeeDto>) response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        assert body != null;
        Assertions.assertThat(body.size()).isEqualTo(3);
        }


    @Test
    void getEmployeeById() {
        UserController userController = new UserController(new UserService(new UserRepository() {
            @Override
            public Optional<User> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<User> findAll() {
                return List.of(
                        new User("20L", "Ani", "Mkrtumyan", "ani@gmail.com"),
                        new User("21L", "Annabelle", "Petrosian", "annabelle@gmail.com"),
                        new User("22L", "Annie", "Hakian", "annie@gmail.com"));
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(String s) {
                return null;
            }

            @Override
            public User getById(String s) {
                return null;
            }

            @Override
            public User getReferenceById(String s) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }
        }), new ValidationService());
        var response = userController.getEmployeeById(1L);
        var body = (List<EmployeeDto>) response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        assert body != null;
        Assertions.assertThat(body.get(1));
        }


    @Test
    void getAllEmployer() {
        UserController userController = new UserController(new UserService(new UserRepository() {
            @Override
            public Optional<User> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<User> findAll() {
                        return List.of(new User("10L", "Hovhannes", "Chobanyan", "hovhannes@gmail.com"),
                        new User("11L", "Erik", "Minasian", "erik@gmail.com"),
                        new User("12L", "Zaruhi", "Tshitoyan", "zaruhi@gmail.com"));
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(String s) {
                return null;
            }

            @Override
            public User getById(String s) {
                return null;
            }

            @Override
            public User getReferenceById(String s) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }
        }), new ValidationService());
        var response = userController.getAllEmployer();
        var body = (List<EmployeeDto>) response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        assert body != null;
        Assertions.assertThat(body.size()).isEqualTo(3);
        }

    @Test
    void getEmployerById() {
        UserController userController = new UserController(new UserService(new UserRepository() {
            @Override
            public Optional<User> findById(String id) {
                return Optional.empty();
            }

            @Override
            public List<User> findAll() {
                return List.of(
                        new User("10L", "Hovhannes", "Chobanyan", "hovhannes@gmail.com"),
                        new User("11L", "Erik", "Minasian", "erik@gmail.com"),
                        new User("12L", "Zaruhi", "Tshitoyan", "zaruhi@gmail.com"));
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<String> strings) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<User> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<String> strings) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(String s) {
                return null;
            }

            @Override
            public User getById(String s) {
                return null;
            }

            @Override
            public User getReferenceById(String s) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public boolean existsById(String s) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(String s) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends String> strings) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }
        }), new ValidationService());
        var response = userController.getEmployerById(11L);
        var body = (List<EmployeeDto>) response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        assert body != null;
        Assertions.assertThat(body.get(11));
        }
    }