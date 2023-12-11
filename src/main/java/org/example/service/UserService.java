package org.example.service;

import org.example.dto.ProfileDTO;
import org.example.repository.ProfileRepository;
import org.example.utils.ScannerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {


    @Autowired
    ProfileRepository profileRepository;



    public ProfileDTO login(ProfileDTO profileDTO) {
        ProfileDTO profile = profileRepository.login(profileDTO);
        return profile;
    }

    public boolean registration(ProfileDTO profile) {
        boolean result = profileRepository.registration(profile);
        return result;
    }

//    public List<ProfileDTO> profilee() {
//        List<ProfileDTO> profileDTOList = profileRepository.getprfile_list();
//        return profileDTOList;
//    }


    public List<ProfileDTO> getProfillist() {
        List<ProfileDTO> cardList =profileRepository.getprfile_list();
        return cardList;
    }

    public void updateProfile(ProfileDTO profileDTO, String psw) {
        boolean b = profileRepository.updateProfil(profileDTO, psw);
        if (b){
            System.out.println("Profile status update 👌");
        }else {

        }
        System.out.println("not update");
    }
}
