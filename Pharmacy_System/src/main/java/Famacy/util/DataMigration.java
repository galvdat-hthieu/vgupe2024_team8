package Famacy.util;

import Famacy.model.Item;
import Famacy.model.Medicine;
import Famacy.model.Consumable;
import Famacy.repository.ItemRepository;
import Famacy.repository.MedicineRepository;
import Famacy.repository.ConsumableRepository;

import java.util.List;

public class DataMigration {

    public static void migrateData() {
        ItemRepository itemRepository = new ItemRepository();
        MedicineRepository medicineRepository = new MedicineRepository();
        ConsumableRepository consumableRepository = new ConsumableRepository();

        // Migrate Medicine data
        List<Medicine> medicines = medicineRepository.findAll();
        for (Medicine medicine : medicines) {
            Item item = new Item();
            item.setName(medicine.getId().getName());
            item.setPrice(medicine.getPrice());
            item.setType("Medicine");
            itemRepository.save(item);
        }

        // Migrate Consumable data
        List<Consumable> consumables = consumableRepository.findAll();
        for (Consumable consumable : consumables) {
            Item item = new Item();
            item.setName(consumable.getId().getName());
            item.setPrice(consumable.getPrice());
            item.setType("Consumable");
            itemRepository.save(item);
        }

        System.out.println("Data migration completed successfully.");
    }

    public static void main(String[] args) {
        migrateData();
    }
}
