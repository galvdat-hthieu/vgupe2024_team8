/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.service;


import Famacy.model.Medicine;
import Famacy.model.MedicineId;
import Famacy.repository.MedicineRepository;

import java.util.List;

public class MedicineService {
    private MedicineRepository medicineRepository;

    public MedicineService() {
        this.medicineRepository = new MedicineRepository();
    }

    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicineById(MedicineId id) {
        return medicineRepository.findById(id);
    }

    public void deleteMedicine(MedicineId id) {
        medicineRepository.delete(id);
    }
}

