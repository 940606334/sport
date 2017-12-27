package cn.yearcon.sport.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传控制器
 *
 * @author itguang
 * @create 2017-12-25 10:26
 **/
@Controller
public class UploadController {
    @RequestMapping(value="/toUpload")
    public String toUpload(){
        return "sport/upload";
    }

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public String upload(Model model, MultipartFile file, HttpServletRequest request){
        if(file==null){
            return "redirect:/toUpload";
        }
        String filename=file.getOriginalFilename();
        String path = request.getSession().getServletContext().getRealPath("/");
        System.out.println(path);
        path=path+filename;
        //path=path+"static"+File.separator+filename;
        try {
            file.transferTo(new File(path));
            model.addAttribute("message","上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message","上传失败");
        }
        //System.out.println("filename="+filename);
        return "sport/upload";
    }

}
