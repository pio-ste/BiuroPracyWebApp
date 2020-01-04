package com.biuropracy.demo.service;

import com.biuropracy.demo.model.JobOffer;
import com.biuropracy.demo.model.ProfileProposition;
import com.biuropracy.demo.model.User;
import com.biuropracy.demo.repository.ProfilePropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilePropositionService {

    @Autowired
    ProfilePropositionRepository profilePropositionRepository;

    public ProfileProposition getProfilePropById(Integer id) {
        Optional<ProfileProposition> profilePropOpt = profilePropositionRepository.findById(id);
        if (profilePropOpt.isPresent()){
            return profilePropOpt.get();
        } else {
            throw new RuntimeException("Id ProfileProposition nie znalezione");
        }
    }

    public ProfileProposition updateProfileProp(ProfileProposition profileProposition) {
        Optional<ProfileProposition> profilePropOpt = profilePropositionRepository.findById(profileProposition.getIdProfileProposition());
        if (profilePropOpt.isPresent()) {
            ProfileProposition newProfileProp = profilePropOpt.get();
            newProfileProp.setContactType(profileProposition.getContactType());
            newProfileProp.setDecision(profileProposition.getDecision());

            newProfileProp = profilePropositionRepository.save(newProfileProp);
            return newProfileProp;
        } else {
            profileProposition = profilePropositionRepository.save(profileProposition);
            return profileProposition;
        }
    }

    public ProfileProposition createProfileProp(ProfileProposition profileProposition, User user, JobOffer jobOffer){
        profileProposition.setUser(user);
        profileProposition.setJobOffer(jobOffer);
        profileProposition=profilePropositionRepository.save(profileProposition);
        return profileProposition;
    }

    public void deleteProfileProp(Integer id) {
        Optional<ProfileProposition> profilePropOpt = profilePropositionRepository.findById(id);
        if (profilePropOpt.isPresent()) {
            profilePropositionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brak ProfileProposition o tym id");
        }
    }
}
