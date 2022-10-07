package org.cnss.helpers;

@FunctionalInterface

public interface EnumValues {
    enum status { PENDING, REFUSED, ACCEPTED }
    enum documentType { ANALYSE, RADIO, SCANNER }
    enum users {ADMIN, AGENT, PATIENT}
    String setValue();
}
