/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Famacy.util;

/**
 *
 * @author ADMIN
 */
public class DateTimeAdd0Util {
    public String add0(String originalDate){
        String newDate = originalDate;
        if (originalDate != null && !originalDate.isEmpty()) {
            // Check if the date format needs adjustment (e.g., single-digit day or month)
            if (originalDate.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
                // Adjust the format to "dd/MM/yyyy" if it matches the pattern for single-digit day or month
                String[] parts = originalDate.split("/");
                if (parts.length == 3) {
                    String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
                    String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
                    newDate = day + "/" + month + "/" + parts[2];
                }
            }
        } else {
            // Handle cases where birth date is null or empty
            System.err.println("Date is null or empty");
        }
        return newDate;
    }
}
