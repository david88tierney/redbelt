package com.codingdojo.redbelt.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.redbelt.models.Idea;

public interface IdeaRepository extends CrudRepository<Idea, Long>{
    List<Idea> findAll();
    Idea findByName(String name);
}
