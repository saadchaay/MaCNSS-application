package org.cnss.helpers;

@FunctionalInterface

public interface EnumValues {
    enum status { PENDING, REFUSED, VALIDATED }
    enum documentType { ANALYSE, RADIO, SCANNER }
    String setValue();
}
