package com.musicode.criminalintent.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> list;

    private CrimeLab() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("title" + i);
            crime.setSolved(i % 2 == 0);
            list.add(crime);
        }
    }

    public static CrimeLab getInstance() {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab();
        }
        return sCrimeLab;
    }

    public List<Crime> getAll() {
        return list;
    }

    public Crime get(UUID id) {
        for (Crime crime : list) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

}
