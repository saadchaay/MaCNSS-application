package org.cnss;

@FunctionalInterface

interface EnumValues {
    enum status { PENDING, REFUSED, VALIDATED }
    enum documentType { ANALYSE, RADIO, SCANNER }
    String setValue();
}
