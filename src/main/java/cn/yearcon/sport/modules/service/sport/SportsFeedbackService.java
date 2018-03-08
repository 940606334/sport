package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsFeedbackEntity;
import cn.yearcon.sport.modules.repository.sport.SportsFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author itguang
 * @create 2017-12-13 14:56
 **/
@Service
public class SportsFeedbackService {
    @Autowired
    private SportsFeedbackRepository sportsFeedbackRepository;
    public void saveFeedback(SportsFeedbackEntity sportsFeedbackEntity){
        sportsFeedbackRepository.save(sportsFeedbackEntity);
    }
}
