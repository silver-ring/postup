package com.appzone.postup.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mohamed Morsy
 */
@Repository
public interface  CommentRepository extends JpaRepository<CommentEntity, Long> {        
    
    
}
