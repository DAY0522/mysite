package mysite.service;

import mysite.repository.GuestbookLogRepository;
import mysite.repository.GuestbookRepository;
import mysite.vo.GuestbookVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuestbookService {

    private GuestbookRepository guestbookRepository;
    private GuestbookLogRepository guestbookLogRepository;

    public GuestbookService(GuestbookRepository guestbookRepository, GuestbookLogRepository guestbookLogRepository) {
        this.guestbookRepository = guestbookRepository;
        this.guestbookLogRepository = guestbookLogRepository;
    }

    public List<GuestbookVo> getContentsList() {
        return guestbookRepository.findAll();
    }

    @Transactional
    public void deleteContents(Long id, String password) {
        GuestbookVo vo = guestbookRepository.findById(id);
        if (vo == null) {
            return;
        }

        int count = guestbookRepository.deleteByIdAndPassword(id, password);
        if (count == 1) {
            guestbookLogRepository.updateByRegDate(vo.getRegDate());
        }
    }

    @Transactional
    public void addContents(GuestbookVo vo) {
        int count = guestbookLogRepository.update();
        if (count == 0) {
            guestbookLogRepository.insert();
        }
        guestbookRepository.insert(vo);
    }
}
