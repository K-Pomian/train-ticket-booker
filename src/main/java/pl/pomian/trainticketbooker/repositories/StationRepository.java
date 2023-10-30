package pl.pomian.trainticketbooker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pomian.trainticketbooker.models.Station;

@Repository
public interface StationRepository extends MongoRepository<Station, String> {
}
