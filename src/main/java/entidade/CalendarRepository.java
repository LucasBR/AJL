package entidade;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer>  {

	@Query(value = "select * from calendar c where date_trunc('day',c.start_time) = :date order by c.start_time desc", nativeQuery = true)
	List<Calendar> findByStartTime(@Param("date") Date startTime);

    public Calendar save(Calendar calendar);

    public List<Calendar> findAll();

}
