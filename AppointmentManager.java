package com.domebarbershop;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    private List<Appointment> appointments;
    private final String FILE = "appointments.dat";

    public AppointmentManager(){
        appointments = new ArrayList<>();
        loadAppointments();
    }

    public boolean addAppointment(Appointment a){
        // Check conflict
        for(Appointment appt : appointments){
            if(appt.getBarberName().equals(a.getBarberName()) &&
               appt.getDate().equals(a.getDate()) &&
               appt.getTime().equals(a.getTime())) return false;
        }
        appointments.add(a);
        saveAppointments();
        return true;
    }

    public void removeAppointment(Appointment a){
        appointments.remove(a);
        saveAppointments();
    }

    public List<Appointment> getAppointments(){ return appointments; }

    public void saveAppointments(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))){
            oos.writeObject(appointments);
        } catch(Exception e){ e.printStackTrace(); }
    }

    @SuppressWarnings("unchecked")
    public void loadAppointments(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))){
            appointments = (List<Appointment>) ois.readObject();
        } catch(Exception e){
            appointments = new ArrayList<>();
        }
    }
}
