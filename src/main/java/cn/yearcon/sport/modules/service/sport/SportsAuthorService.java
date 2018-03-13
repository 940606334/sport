package cn.yearcon.sport.modules.service.sport;

import cn.yearcon.sport.modules.entity.sport.user.SportsAuthor;
import cn.yearcon.sport.modules.repository.sport.SportsAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayong
 * @create 2018-03-09 9:10
 **/
@Service
public class SportsAuthorService {
    @Autowired
    private SportsAuthorRepository sportsAuthorRepository;

    public void save(SportsAuthor sportsAuthor){
        sportsAuthorRepository.save(sportsAuthor);
    }

    public SportsAuthor findById(Integer vipid){
        return sportsAuthorRepository.findOne(vipid);
    }
}
