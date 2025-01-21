package mysite.service;

import mysite.repository.AdminRepository;
import mysite.vo.AdminVo;
import org.springframework.stereotype.Service;

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
