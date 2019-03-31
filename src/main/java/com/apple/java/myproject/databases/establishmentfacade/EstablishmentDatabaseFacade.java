package com.apple.java.myproject.databases.establishmentfacade;

import com.apple.java.myproject.Establishments;
import com.apple.java.myproject.exception.EstablishmentBaseException;

public interface EstablishmentDatabaseFacade {
    void createEstablishment(Establishments establishment) throws EstablishmentBaseException;
    void updateEstablishment(Establishments establishment) throws EstablishmentBaseException;
    /*void deleteEstablishment(Integer id) throws EstablishmentBaseException;
    void selectEstablishment(Integer id) throws EstablishmentBaseException;*/
}
