package mysite.service;

import mysite.repository.GuestbookRepository;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestbookService {

    private GuestbookRepository guestbookRepository;

    public GuestbookService(GuestbookRepository guestbookRepository) {
        this.guestbookRepository = guestbookRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    public void deleteContents(Long id, String password) {
        guestbookRepository.deleteByIdAndPassword(id, password);
    }

    public void addContents(GuestbookVo vo) {
        guestbookRepository.insert(vo);
    }
}
