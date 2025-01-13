package mysite.service;

import mysite.repository.AdminRepository;
import mysite.vo.AdminVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminVo getContent() {
        return adminRepository.findContent();
    }


    public void update(AdminVo vo) {
        adminRepository.update(vo);
    }
}
