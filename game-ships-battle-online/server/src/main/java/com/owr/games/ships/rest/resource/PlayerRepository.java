package com.owr.games.ships.rest.resource;

import com.owr.games.ships.db.entities.GameEntity;
import com.owr.games.ships.db.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {




    /*List<Book> findByAuthorContainingIgnoreCaseAndNameContainingIgnoreCase(String author, String name);

	List<Book> findByAuthorContainingIgnoreCaseOrNameContainingIgnoreCase(String author, String name);


	// Performance killer SQL :)

	@Query("SELECT b FROM Book b WHERE (b.count > 0 OR :available = TRUE)"
			+ " AND (UPPER(b.name) LIKE UPPER(CONCAT('%',:searchtext,'%'))"
			+ " OR UPPER(b.author) LIKE UPPER(CONCAT('%',:searchtext,'%'))"
			+ " OR UPPER(b.codeISBN) LIKE UPPER(CONCAT('%',:searchtext,'%')))")
    List<Book> fetchBooks(@Param("searchtext") String searchtext, @Param("available") boolean onlyAvailable);*/

}
