package com.example.ipwa02_re;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

@Named
@ApplicationScoped
public class r4t  implements Serializable {

    public static final String persistenceUnitName = "r4t";

    public EntityManager em;

}
