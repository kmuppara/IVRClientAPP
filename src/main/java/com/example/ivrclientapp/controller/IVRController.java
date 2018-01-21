package com.example.ivrclientapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.ivrclientapp.bo.IVRBO;
import com.example.ivrclientapp.vo.FileVO;

@Controller
public class IVRController {

	@Autowired
	IVRBO ivrBO;
	
	@RequestMapping(value="/ivrservice", method=RequestMethod.GET)
	public ModelAndView fileDetails(@RequestParam("fileNm") String fileNm) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ivrhome");
		FileVO fileVO = ivrBO.fileDetails(fileNm);
		mv.addObject("fileVO", fileVO);
		int mins = fileVO.getDuration() / 60;
		int hrs = fileVO.getDuration() / 3600;
		mv.addObject("mins", mins);
		mv.addObject("hrs", hrs);
		fileVO.setSize(fileVO.getSize() / 1024);
		return mv;
	}
	@RequestMapping(value="/ivrfiles", method=RequestMethod.GET)
	public ModelAndView filesList() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ivrhome");
		List<String> fileNms = ivrBO.getFiles();
		mv.addObject("fileNms", fileNms);
		return mv;
	}
	@RequestMapping(value="/ivrservice", method=RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile multipartfile)throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ivrhome");
		String fileNm = multipartfile.getOriginalFilename();
		if (fileNm.contains(".wav")) {
			FileVO fileVO = new FileVO();
			fileVO.setFileNm(fileNm);
			File file = new File(fileNm);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartfile.getBytes());
			fos.close();
			fileVO.setFile(file);
			ivrBO.fileUpload(fileVO);
			mv.addObject("msg", fileNm + " is uploaded successfully");
		} else {
			mv.addObject("msg", "Check file format(.wav only)");
		}
		return mv;
	}
	
}
