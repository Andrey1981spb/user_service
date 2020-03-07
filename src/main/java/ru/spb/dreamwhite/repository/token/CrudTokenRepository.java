package ru.spb.dreamwhite.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.dreamwhite.model.Token;

@Repository
public interface CrudTokenRepository extends JpaRepository<Token, Integer> {

Token getByToken (String token);

    @Transactional
    @Modifying
    @Query("DELETE FROM Token t WHERE t.token=:token")
    int delete (@Param("token") String token);

}
