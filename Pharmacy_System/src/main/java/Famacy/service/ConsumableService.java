/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.service;


import Famacy.model.Consumable;
import Famacy.model.ConsumableId;
import Famacy.repository.ConsumableRepository;

import java.util.List;

public class ConsumableService {
    private ConsumableRepository consumableRepository;

    public ConsumableService() {
        this.consumableRepository = new ConsumableRepository();
    }

    public Consumable saveConsumable(Consumable consumable) {
        return consumableRepository.save(consumable);
    }

    public List<Consumable> getAllConsumables() {
        return consumableRepository.findAll();
    }
    
    public Consumable getConsumableByName(String name){
        return consumableRepository.findConsumableByName(name);
    }
    
    public Consumable getConsumableById(ConsumableId id) {
        return consumableRepository.findById(id);
    }

    public void updateConsumable(Consumable consumable) {
        consumableRepository.save(consumable);
    }

    public void deleteConsumable(ConsumableId id) {
        consumableRepository.delete(id);
    }

    public List<Consumable> searchConsumables(String name, String supplier) {
        return consumableRepository.searchConsumables(name, supplier);
    }
    
    public void convertDateIfNeeded() {
        consumableRepository.dateConvert();
    }
}

