package Famacy.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceRepository {

    public static class Invoice {
        private final int patientNo;
        private final String name;
        private final int id;
        private final int age;
        private final String healthInsuranceStatus;
        private final String paymentStatus;
        private final String prescriptionDetails;

        public Invoice(int patientNo, String name, int id, int age, String healthInsuranceStatus, String paymentStatus, String prescriptionDetails) {
            this.patientNo = patientNo;
            this.name = name;
            this.id = id;
            this.age = age;
            this.healthInsuranceStatus = healthInsuranceStatus;
            this.paymentStatus = paymentStatus;
            this.prescriptionDetails = prescriptionDetails;
        }

        public int getPatientNo() {
            return patientNo;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public String getHealthInsuranceStatus() {
            return healthInsuranceStatus;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getPrescriptionDetails() {
            return prescriptionDetails;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "patientNo=" + patientNo +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    ", healthInsuranceStatus='" + healthInsuranceStatus + '\'' +
                    ", paymentStatus='" + paymentStatus + '\'' +
                    ", prescriptionDetails='" + prescriptionDetails + '\'' +
                    '}';
        }
    }

    public Optional<Invoice> findInvoiceByPatientNo(int patientNo) {
        // Return a static invoice for now
        if (patientNo == 1) {
            return Optional.of(new Invoice(1, "Holy Shit Khoi Is Gay", 1001, 20, "No", "No", "N/A"));
        }
        return Optional.empty();
    }

    public List<Service> findServicesByPatientNo(int patientNo) {
        // Return a static list of services for now
        List<Service> services = new ArrayList<>();
        if (patientNo == 1) {
            services.add(new Service("Medicine 1", 20.00));
            services.add(new Service("Medicine 2", 10.00));
            services.add(new Service("Medicine 3", 6.00));
        }
        return services;
    }

    public static class Service {
        private final String name;
        private final double cost;

        public Service(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return name + " - $" + cost;
        }
    }
}
