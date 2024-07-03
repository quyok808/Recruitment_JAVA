package com.example.OnlyA.service;

import com.example.OnlyA.model.Candidate;
import com.example.OnlyA.model.User;
import com.example.OnlyA.repository.candidateRepository;
import com.example.OnlyA.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
    private static final List<String> ALLOWED_FILE_TYPES = List.of("image/png", "image/jpeg", "application/pdf");

    @Autowired
    private candidateRepository candidateRepository;

    @Autowired
    private userRepo userRepository;

    @Autowired
    private userService userService;

    public Candidate getCurrentCandidate() {
        String currentUsername = userService.getLoggedInUsername();
        Optional<User> userOptional = userRepository.findByUsername(currentUsername);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return candidateRepository.findByUser(user).orElse(null);
        }
        return null;
    }

    public void addCandidateProfile(Candidate candidate, MultipartFile cvFile, MultipartFile certificateFile, MultipartFile awardFile) {
        String currentUsername = userService.getLoggedInUsername();
        Optional<User> userOptional = userRepository.findByUsername(currentUsername);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (candidateRepository.findByUser(user).isPresent()) {
                throw new RuntimeException("User already has a profile");
            }

            candidate.setUser(user);
            handleFiles(candidate, cvFile, certificateFile, awardFile);
            candidateRepository.save(candidate);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void updateCandidateProfile(Candidate candidate, MultipartFile cvFile, MultipartFile certificateFile, MultipartFile awardFile) {
        Optional<Candidate> existingCandidate = candidateRepository.findById(candidate.getId());

        if (existingCandidate.isPresent()) {
            Candidate candidateToUpdate = existingCandidate.get();
            candidateToUpdate.setFullName(candidate.getFullName());
            candidateToUpdate.setExperience(candidate.getExperience());
            candidateToUpdate.setMajor(candidate.getMajor());
            candidateToUpdate.setSkills(candidate.getSkills());

            handleFiles(candidateToUpdate, cvFile, certificateFile, awardFile);
            candidateRepository.save(candidateToUpdate);
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }

    private void handleFiles(Candidate candidate, MultipartFile cvFile, MultipartFile certificateFile, MultipartFile awardFile) {
        try {
            if (cvFile != null && !cvFile.isEmpty()) {
                validateFileType(cvFile);
                String cvFileName = saveFile(cvFile);
                candidate.setCvFileName(cvFileName);
            }

            if (certificateFile != null && !certificateFile.isEmpty()) {
                validateFileType(certificateFile);
                String certificateFileName = saveFile(certificateFile);
                candidate.setCertificateFileName(certificateFileName);
            }

            if (awardFile != null && !awardFile.isEmpty()) {
                validateFileType(awardFile);
                String awardFileName = saveFile(awardFile);
                candidate.setAwardFileName(awardFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateFileType(MultipartFile file) {
        if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("Invalid file type");
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + uniqueFilename);
        Files.write(path, bytes);
        return uniqueFilename;
    }
}
