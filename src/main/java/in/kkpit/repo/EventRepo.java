package in.kkpit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kkpit.entity.Event;
@Repository
public interface EventRepo extends JpaRepository<Event,Long> {

}
