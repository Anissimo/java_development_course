package org.example.lab1;

import org.example.lab1.entities.Car;
import org.example.lab1.exeptions.DuplicateModelNameException;
import org.example.lab1.exeptions.ModelPriceOutOfBoundsException;
import org.example.lab1.exeptions.NoSuchModelNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Car")
public class CarTest {

    @Test
    @DisplayName("Добавление и удаление модели у автомобиля")
    public void testAddAndRemoveModel() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);
        car.addModel("Camry", 30000);

        assertAll("Test add and remove model",
                  () -> assertArrayEquals(new String[]{"Corolla", "Camry"}, car.getModelNames(), "Model names should match"),
                  () -> assertEquals(20000, car.getModelPrice("Corolla"), "Price for Corolla should be 20000"),
                  () -> assertEquals(30000, car.getModelPrice("Camry"), "Price for Camry should be 30000")
        );

        car.removeModel("Corolla");

        assertAll("Test after removing a model",
                  () -> assertArrayEquals(new String[]{"Camry"}, car.getModelNames(), "Model names should match after removal")
        );
    }

    @Test
    @DisplayName("Установка цены модели для существующей модели")
    public void testSetModelPrice() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);
        car.setModelPrice("Corolla", 22000);

        assertAll("Test set model price",
                  () -> assertEquals(22000, car.getModelPrice("Corolla"), "Price for Corolla should be updated to 22000")
        );
    }

    @Test
    @DisplayName("Добавление дублирующей модели должно привести к исключению DuplicateModelNameException")
    public void testAddDuplicateModel() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);

        assertThrows(DuplicateModelNameException.class, () -> car.addModel("Corolla", 22000), "Adding a duplicate model should throw an exception");
    }

    @Test
    @DisplayName("Удаление несуществующей модели должно привести к исключению NoSuchModelNameException")
    public void testRemoveNonExistentModel() {
        Car car = new Car("Toyota", 2);

        assertThrows(NoSuchModelNameException.class, () -> car.removeModel("NonExistentModel"), "Removing a non-existent model should throw an exception");
    }

    @Test
    @DisplayName("Установка отрицательной цены должна привести к исключению ModelPriceOutOfBoundsException")
    public void testSetNegativePrice() {
        Car car = new Car("Toyota", 2);
        car.addModel("Corolla", 20000);

        assertThrows(ModelPriceOutOfBoundsException.class, () -> car.setModelPrice("Corolla", -1000), "Setting a negative price should throw an exception");
    }
}
