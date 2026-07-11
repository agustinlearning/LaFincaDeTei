package visual;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// 1. Creamos el modelo personalizado
public class LocalDateSpinnerModel extends AbstractSpinnerModel {
    private LocalDate value;
    private final ChronoUnit stepUnit;

    public LocalDateSpinnerModel(LocalDate initialValue, ChronoUnit stepUnit) {
        this.value = initialValue != null ? initialValue : LocalDate.now();
        this.stepUnit = stepUnit;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof LocalDate && !value.equals(this.value)) {
            this.value = (LocalDate) value;
            fireStateChanged();
        }
    }

    @Override
    public Object getNextValue() {
        return value.plus(1, stepUnit);
    }

    @Override
    public Object getPreviousValue() {
        return value.minus(1, stepUnit);
    }
}
