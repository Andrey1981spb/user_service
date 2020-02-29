package ru.spb.dreamwhite.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.spb.dreamwhite.model.Token;

@Repository
public interface CrudTokenRepository extends JpaRepository<Token, Integer> {

Token getByToken (String token);

}
