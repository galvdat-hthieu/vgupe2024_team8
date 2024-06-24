/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.service;


import Famacy.model.Consumable;
import Famacy.repository.ConsumableRepository;

import java.util.List;

public class ConsumableService {
    private ConsumableRepository consumableRepository;

    public ConsumableService() {
        this.consumableRepository = new ConsumableRepository();
    }

    public Consumable saveConsumable(Consumable consumable) {
        return consumableRepository.saveOrUpdate(consumable);
    }

    public List<Consumable> getAllConsumables() {
        return consumableRepository.findAll();
    }

    public Consumable getConsumableById(String name, String supplier) {
        return consumableRepository.findById(name, supplier);
    }

    public void updateConsumable(Consumable consumable) {
        consumableRepository.saveOrUpdate(consumable);
    }

    public void deleteConsumable(String name, String supplier) {
        consumableRepository.delete(name, supplier);
    }

    public List<Consumable> searchConsumables(String name, String supplier) {
        return consumableRepository.searchConsumables(name, supplier);
    }
}

