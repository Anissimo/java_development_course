package org.example.lab2;

import org.example.lab1.entities.Car;
import org.example.lab1.entities.Motorcycle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab2Test {

    @Test
    @DisplayName("Тест сериализации и десериализации объекта Motorcycle")
    public void testMotorcycleSerialization() throws IOException, ClassNotFoundException {
        Motorcycle motorcycle = new Motorcycle("Harley-Davidson");
        motorcycle.addModel("Sportster", 15000);
        motorcycle.addModel("Softail", 18000);

        // Сериализация
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("motorcycle.dat"))) {
            out.writeObject(motorcycle);
        }

        // Десериализация
        Motorcycle deserializedMotorcycle;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("motorcycle.dat"))) {
            deserializedMotorcycle = (Motorcycle) in.readObject();
        }

        assertAll(
                () -> assertEquals(motorcycle.getBrand(), deserializedMotorcycle.getBrand()),
                () -> assertArrayEquals(motorcycle.getModelNames(), deserializedMotorcycle.getModelNames())
        );
    }


}
