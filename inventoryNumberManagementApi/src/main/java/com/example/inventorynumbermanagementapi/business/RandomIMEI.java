package com.example.inventorynumbermanagementapi.business;

import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class RandomIMEI {
    private static final int MEI_LENGTH = 6;
    private static Random rand = new Random();
    // Define fictional TACs for different brands
    private static final Map<String, String> brandTACs = new HashMap<>();
    static {
        brandTACs.put("Apple", "12345678");
        brandTACs.put("Samsung", "87654321");
        brandTACs.put("Xiaomi", "23456789");
        brandTACs.put("Realme", "98765432");
        brandTACs.put("Oppo", "34567890");
        brandTACs.put("Oneplus", "45678901");
        brandTACs.put("Vivo", "56789012");
        brandTACs.put("GooglePixel", "67890123");
    }

    public static String generate(String brand) {
        if (!brandTACs.containsKey(brand)) {
            throw new IllegalArgumentException("Brand not supported.");
        }

        StringBuilder imei = new StringBuilder();

        // Use the TAC for the specified brand
        imei.append(brandTACs.get(brand));

        // Generate MEI (Mobile Equipment Identifier)
        imei.append(generateRandomNumericString(MEI_LENGTH));

        // Calculate Luhn check digit
        int[] imeiDigits = imei.chars().map(Character::getNumericValue).toArray();
        int luhnCheckDigit = calculateLuhnCheckDigit(imeiDigits);
        imei.append(luhnCheckDigit);

        return imei.toString();
    }

    private static String generateRandomNumericString(int length) {
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    private static int calculateLuhnCheckDigit(int[] digits) {
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i];
            if (i % 2 == 1) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }
}
