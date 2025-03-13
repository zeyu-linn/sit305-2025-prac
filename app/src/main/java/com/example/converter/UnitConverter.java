package com.example.converter;

public class UnitConverter {
    public static double convert(double value, String fromUnit, String toUnit, String category) {
        switch (category) {
            case "Length":
                return convertLength(value, fromUnit, toUnit);
            case "Weight":
                return convertWeight(value, fromUnit, toUnit);
            case "Temperature":
                return convertTemperature(value, fromUnit, toUnit);
            default:
                return 0;
        }
    }

    private static double convertLength(double value, String fromUnit, String toUnit) {
        // Convert to centimeters first
        double cm;
        switch (fromUnit) {
            case "Inch": cm = value * 2.54; break;
            case "Foot": cm = value * 30.48; break;
            case "Yard": cm = value * 91.44; break;
            case "Mile": cm = value * 160934; break;
            case "Centimeter": cm = value; break;
            case "Kilometer": cm = value * 100000; break;
            default: return 0;
        }

        switch (toUnit) {
            case "Inch": return cm / 2.54;
            case "Foot": return cm / 30.48;
            case "Yard": return cm / 91.44;
            case "Mile": return cm / 160934;
            case "Centimeter": return cm;
            case "Kilometer": return cm / 100000;
            default: return 0;
        }
    }

    private static double convertWeight(double value, String fromUnit, String toUnit) {
        // Convert to kilograms first
        double kg;
        switch (fromUnit) {
            case "Pound": kg = value * 0.453592; break;
            case "Ounce": kg = value * 0.0283495; break;
            case "Ton": kg = value * 907.185; break;
            case "Kilogram": kg = value; break;
            case "Gram": kg = value / 1000; break;
            default: return 0;
        }

        switch (toUnit) {
            case "Pound": return kg / 0.453592;
            case "Ounce": return kg / 0.0283495;
            case "Ton": return kg / 907.185;
            case "Kilogram": return kg;
            case "Gram": return kg * 1000;
            default: return 0;
        }
    }

    private static double convertTemperature(double value, String fromUnit, String toUnit) {
        // Convert to Celsius first
        double celsius = value;
        switch (fromUnit) {
            case "Fahrenheit": celsius = (value - 32) / 1.8; break;
            case "Kelvin": celsius = value - 273.15; break;
        }

        switch (toUnit) {
            case "Celsius": return celsius;
            case "Fahrenheit": return (celsius * 1.8) + 32;
            case "Kelvin": return celsius + 273.15;
            default: return 0;
        }
    }
}
