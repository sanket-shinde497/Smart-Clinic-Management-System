package com.project.back_end.services;

import com.project.back_end.models.Prescription;
import com.project.back_end.repo.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    @Autowired(required = false)
    private PrescriptionRepository prescriptionRepository;

    /**
     * ✅ Save a new prescription.
     *
     * @param prescription The prescription object to save
     * @return 1 on success, 0 on failure, -1 if repository not available
     */
    public int savePrescription(Prescription prescription) {
        if (prescriptionRepository == null) {
            return -1; // Mongo not configured in this environment
        }
        try {
            prescriptionRepository.save(prescription);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * ✅ Get a prescription by appointment ID.
     *
     * @param appointmentId The appointment ID to look up
     * @return Prescription or null if not available
     */
    public Prescription getPrescription(Long appointmentId) {
        if (prescriptionRepository == null) {
            return null;
        }
        try {
            java.util.List<Prescription> list = prescriptionRepository.findByAppointmentId(appointmentId);
            return (list != null && !list.isEmpty()) ? list.get(0) : null;
        } catch (Exception e) {
            return null;
        }
    }

}
