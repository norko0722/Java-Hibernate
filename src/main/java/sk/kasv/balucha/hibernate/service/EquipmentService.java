package sk.kasv.balucha.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.kasv.balucha.hibernate.dao.AppDAO;
import sk.kasv.balucha.hibernate.entities.Equipment;

import java.util.List;

@Service
public class EquipmentService {

    private final AppDAO appDAO;

    @Autowired
    public EquipmentService(AppDAO appDAO) {
        this.appDAO = appDAO;
    }

    public Equipment saveEquipment(Equipment equipment) {
        appDAO.saveEquipment(equipment);
        return equipment;
    }

    public Equipment findEquipmentById(int id) {
        return appDAO.findEquipmentById(id);
    }

    public List<Equipment> findAllEquipment() {
        return appDAO.findAllEquipment();
    }
}