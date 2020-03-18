package ru.spb.dreamwhite.repository.trackers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.dreamwhite.model.Tracker;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class TrackerRepository {

    @Autowired
    private CrudTrackerRepository crudTrackerRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Tracker save(Tracker tracker) {
        return crudTrackerRepository.save(tracker);
    }


    public Tracker get(int id) {
        return crudTrackerRepository.findById(id).orElse(null);
    }

    public List<Tracker> getTrackerByParameterOrAll(Map<String, String> paramsMap) {
        String name = paramsMap.get("name");
        Integer user_id = paramsMap.get("user_id")==null?null:Integer.parseInt(paramsMap.get("user_id"));
        String phone = paramsMap.get("phone");
        String email = paramsMap.get("email");

        List<Tracker> trackers = em.createQuery("SELECT tr FROM Tracker tr LEFT JOIN User u ON tr.user_id=u.id WHERE (:user_idValue is null OR tr.user_id=:user_idValue) " +
                "AND (:nameValue is null OR tr.name=:nameValue) " +
                "AND (:phoneValue is null OR u.phone=:phoneValue)" +
                "AND (:emailValue is null OR u.email=:emailValue)", Tracker.class).
                setParameter("user_idValue", user_id).
                setParameter("nameValue", name).
                setParameter("phoneValue", phone).
                setParameter("emailValue", email).
                getResultList();

        return trackers.size() == 0 ? null : trackers;
    }

    public boolean delete(int id) {
        return crudTrackerRepository.delete(id) != 0;
    }

}
